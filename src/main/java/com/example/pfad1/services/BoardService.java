package com.example.pfad1.services;

import com.example.pfad1.entities.board.CommentEntity;
import com.example.pfad1.entities.ImageEntity;
import com.example.pfad1.enums.ImageDownloadResult;
import com.example.pfad1.enums.ImageUploadResult;
import com.example.pfad1.enums.board.*;
import com.example.pfad1.vos.ImageDownloadVo;
import com.example.pfad1.vos.ImageUploadVo;
import com.example.pfad1.vos.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.mappers.IBoardMapper;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;

@Service
public class BoardService {
    private static class Config {
        public static final int ARTICLE_COUNT_PER_PAGE = 10;
        public static final int PAGING_NUMBER = 10;
        public static final String[] ALLOWED_IMAGE_MIMES = new String[]{"image/jpeg", "image/png"};

        private Config() {

        }
    }

    private static class RegExp {
        public static final String CODE = "^([a-z]{2,50})$";
        public static final String PAGE = "^([1-9]([0-9]{0,3})?)$";
        public static final String ARTICLE_INDEX = "^([1-9]([0-9]{0,5})?)$";

        private RegExp() {

        }
    }

    private final IBoardMapper boardMapper;

    @Autowired
    public BoardService(IBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public static boolean checkCode(String s) {
        return s != null && s.matches(RegExp.CODE);
    }

    public static boolean checkPage(String s) {
        return s != null && s.matches(RegExp.PAGE);
    }

    public static boolean checkArticleIndex(String s) {
        return s != null && s.matches(RegExp.ARTICLE_INDEX);
    }

    public void list(ListVo listVo) {
        if (!BoardService.checkCode(listVo.getCode()) ||
                !BoardService.checkPage(String.valueOf(listVo.getPage()))) {
            listVo.setResult(ListResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(listVo);
        if (boardEntity == null) {
            listVo.setResult(ListResult.BOARD_NOT_DEFINED);
            return;
        }
        ArticleEntity[] articleEntities;
        int boardPerCount;
        listVo.setQueryLimit(Config.ARTICLE_COUNT_PER_PAGE);
        listVo.setQueryOffset((listVo.getPage() - 1) * Config.ARTICLE_COUNT_PER_PAGE);
        if (listVo.isSearching()) {
            listVo.setSearchUrl("?&criteria=" + listVo.getCriteria() + "&keyword=" + listVo.getKeyword());
            switch (listVo.getCriteria()) {
                case "content":
                    articleEntities = this.boardMapper.selectArticlesForSearchContent(listVo);
                    boardPerCount = this.boardMapper.selectArticleCountForSearchByContent(listVo);
                    listVo.setBoardPerCount(boardPerCount);
                    listVo.calcMaxPage(boardPerCount, Config.ARTICLE_COUNT_PER_PAGE);
                    listVo.calcStartEndPage(listVo.getPage(), Config.PAGING_NUMBER);
                    break;
                case "writer":
                    articleEntities = this.boardMapper.selectArticlesForSearchWriter(listVo);
                    boardPerCount = this.boardMapper.selectArticleCountForSearchByWriter(listVo);
                    listVo.setBoardPerCount(boardPerCount);
                    listVo.calcMaxPage(boardPerCount, Config.ARTICLE_COUNT_PER_PAGE);
                    listVo.calcStartEndPage(listVo.getPage(), Config.PAGING_NUMBER);
                    break;
                default:
                    articleEntities = this.boardMapper.selectArticlesForSearchTitle(listVo);
                    boardPerCount = this.boardMapper.selectArticleCountForSearchByTitle(listVo);
                    listVo.setBoardPerCount(boardPerCount);
                    listVo.calcMaxPage(boardPerCount, Config.ARTICLE_COUNT_PER_PAGE);
                    listVo.calcStartEndPage(listVo.getPage(), Config.PAGING_NUMBER);
                    break;
            }
        } else {
            articleEntities = this.boardMapper.selectArticlesByList(listVo);
            boardPerCount = this.boardMapper.selectArticleCount(listVo);
            listVo.setBoardPerCount(boardPerCount);
            listVo.calcMaxPage(boardPerCount, Config.ARTICLE_COUNT_PER_PAGE);
            listVo.calcStartEndPage(listVo.getPage(), Config.PAGING_NUMBER);
        }
        listVo.setName(boardEntity.getName());
        listVo.setCode(boardEntity.getCode());
        listVo.setWriteForbidden(boardEntity.isWriteForbidden());
        listVo.setArticles(articleEntities);
        listVo.setResult(ListResult.SUCCESS);
    }

    public void read(ReadVo readVo, UserEntity userEntity) {
        if (!BoardService.checkArticleIndex(String.valueOf(readVo.getIndex()))) {
            readVo.setResult(ReadResult.NORMALIZATION_FAILURE);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(readVo);
        if (articleEntity == null || articleEntity.isDeleted()) {
            readVo.setResult(ReadResult.ARTICLE_NOT_DEFINED);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(articleEntity);
        if (boardEntity.isReadForbidden() && (userEntity == null || !userEntity.isAdmin())) {
            readVo.setResult(ReadResult.READ_NOT_ALLOWED);
            return;
        }
        this.boardMapper.updateArticleView(readVo);
        readVo.setId(articleEntity.getId());
        readVo.setBoardCode(articleEntity.getBoardCode());
        readVo.setCreatedAt(articleEntity.getCreatedAt());
        readVo.setUpdatedAt(articleEntity.getUpdatedAt());
        readVo.setTitle(articleEntity.getTitle());
        readVo.setContent(articleEntity.getContent());
        readVo.setView(articleEntity.getView() + 1);
        readVo.setDeleted(articleEntity.isDeleted());
        readVo.setName(articleEntity.getName());
        readVo.setBoardPage(this.boardMapper.selectArticleCountGreaterThan(readVo) / Config.ARTICLE_COUNT_PER_PAGE + 1);

        CommentEntity[] comments = Arrays.stream(this.boardMapper.selectComments(readVo))
                .filter(x -> !x.isDeleted())
                .toArray(CommentEntity[]::new);
//        AND `deleted_flag` = FALSE 대신 사용

        readVo.setComments(comments);
        readVo.setResult(ReadResult.SUCCESS);
    }

    public void deleteArticle(UserEntity userEntity, DeleteVo deleteVo) {
        if (!BoardService.checkArticleIndex(String.valueOf(deleteVo.getIndex()))) {
            deleteVo.setResult(DeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(deleteVo);
        if (articleEntity == null) {
            deleteVo.setResult(DeleteResult.FAILURE);
            return;
        }
        if (userEntity == null ||
                (!userEntity.isAdmin() && !userEntity.getId().equals(deleteVo.getId()))) {
            deleteVo.setResult(DeleteResult.NOT_ALLOWED);
            return;
        }
        this.boardMapper.deleteArticle(deleteVo);
        deleteVo.setBoardCode(articleEntity.getBoardCode());
        deleteVo.setResult(DeleteResult.SUCCESS);
    }

    public void writeByGet(WriteVo writeVo, UserEntity userEntity) {
        if (!BoardService.checkCode(writeVo.getBoardCode())) {
            writeVo.setResult(WriteResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(writeVo);
        if (boardEntity == null) {
            writeVo.setResult(WriteResult.BOARD_NOT_DEFINED);
            return;
        }
        if (userEntity == null || (boardEntity.isWriteForbidden() && !userEntity.isAdmin())) {
            writeVo.setResult(WriteResult.WRITE_NOT_ALLOWED);
            return;
        }
        writeVo.setResult(WriteResult.SUCCESS);
    }

    public void writeByPost(WriteVo writeVo, UserEntity userEntity) {
        if (!BoardService.checkCode(writeVo.getBoardCode()) ||
                writeVo.getTitle().length() == 0 ||
                writeVo.getContent().length() == 0 ||
                writeVo.getTitle().length() > 100 ||
                writeVo.getContent().length() > 10000 ||
                writeVo.getContent().replaceAll(" ", "").contains("<script")) {
            writeVo.setResult(WriteResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(writeVo);
        if (boardEntity == null) {
            writeVo.setResult(WriteResult.BOARD_NOT_DEFINED);
            return;
        }
        if (userEntity == null || (boardEntity.isWriteForbidden() && !userEntity.isAdmin())) {
            writeVo.setResult(WriteResult.WRITE_NOT_ALLOWED);
            return;
        }
        writeVo.setTitle(HtmlUtils.htmlEscape(writeVo.getTitle())); // html코드가 혹여나 들어가있다면 제거
        writeVo.setId(userEntity.getId());
        this.boardMapper.insertArticle(writeVo);
        writeVo.setIndex(this.boardMapper.selectLastInsertId());
        writeVo.setResult(WriteResult.SUCCESS);
    }

    public void uploadImage(ImageUploadVo imageUploadVo, UserEntity userEntity) {
        if (userEntity == null) {
            imageUploadVo.setResult(ImageUploadResult.NOT_ALLOWED);
            return;
        }
//        Arrays.stream(Config.ALLOWED_IMAGE_MIMES).noneMatch(imageUploadVo.getFile().getContentType()::equals)
//        Arrays.asList(Config.ALLOWED_IMAGE_MIMES).contains(imageUploadVo.getFile().getContentType())
//        위 아래 모두 동일한 구문
//            boolean isMimeValid = false;
//            for(String allowedImageMime : Config.ALLOWED_IMAGE_MIMES) {
//                if(imageUploadVo.getFile().getContentType().equals(allowedImageMime)) {
//                    isMimeValid = true;
//                    break;
//                }
//            }  아래와 동일함.

        if (imageUploadVo.getFile() == null ||
                imageUploadVo.getFile().getContentType() == null ||
                Arrays.stream(Config.ALLOWED_IMAGE_MIMES).noneMatch(imageUploadVo.getFile().getContentType()::equals)) {
            imageUploadVo.setResult(ImageUploadResult.MIME_INVALID);
            return;
        }
        this.boardMapper.insertImage(imageUploadVo);
        imageUploadVo.setIndex(this.boardMapper.selectLastInsertId());
        imageUploadVo.setResult(ImageUploadResult.SUCCESS);
    }

    public void downloadImage(ImageDownloadVo imageDownloadVo) {
        ImageEntity imageEntity = this.boardMapper.selectImage(imageDownloadVo);
        if (imageEntity == null) {
            imageDownloadVo.setResult(ImageDownloadResult.NOT_DEFINED);
            return;
        }
        imageDownloadVo.setCreatedAt(imageEntity.getCreatedAt());
        imageDownloadVo.setFile(imageEntity.getFile());
        imageDownloadVo.setMime(imageEntity.getMime());
        imageDownloadVo.setResult(ImageDownloadResult.SUCCESS);
    }

    public void putComment(UserEntity userEntity, CommentWriteVo commentWriteVo) {
        if (!BoardService.checkCode(commentWriteVo.getBoardCode()) ||
                !BoardService.checkArticleIndex(String.valueOf(commentWriteVo.getArticleIndex())) ||
                commentWriteVo.getContent().length() > 100) {
            commentWriteVo.setResult(CommentWriteResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(commentWriteVo);
        if (boardEntity == null) {
            commentWriteVo.setResult(CommentWriteResult.BOARD_NOT_DEFINED);
            return;
        }
        if (userEntity == null || !userEntity.isAdmin() && boardEntity.isCommentForbidden()) {
            commentWriteVo.setResult(CommentWriteResult.NOT_ALLOWED);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(commentWriteVo);
        if (articleEntity == null || articleEntity.isDeleted()) {
            commentWriteVo.setResult(CommentWriteResult.ARTICLE_NOT_DEFINED);
            return;
        }
        commentWriteVo.setUserId(userEntity.getId());
        commentWriteVo.setContent(HtmlUtils.htmlEscape(commentWriteVo.getContent()));
        this.boardMapper.insertComment(commentWriteVo);
        commentWriteVo.setResult(CommentWriteResult.SUCCESS);
    }

    public void deleteComment(CommentDeleteVo commentDeleteVo, UserEntity userEntity) {
        if (!BoardService.checkCode(commentDeleteVo.getBoardCode()) ||
                !BoardService.checkArticleIndex(String.valueOf(commentDeleteVo.getArticleIndex()))) {
            commentDeleteVo.setResult(CommentDeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null || (!userEntity.isAdmin() && !commentDeleteVo.getUserId().equals(userEntity.getId()))) {
            commentDeleteVo.setResult(CommentDeleteResult.NOT_ALLOWED);
            return;
        }
        this.boardMapper.deleteComment(commentDeleteVo);
        commentDeleteVo.setResult(CommentDeleteResult.SUCCESS);
    }

    public void modifyByGet(ModifyVo modifyVo, UserEntity userEntity) {
        if (!BoardService.checkCode(modifyVo.getBoardCode()) ||
                !BoardService.checkArticleIndex(String.valueOf(modifyVo.getIndex()))) {
            modifyVo.setResult(ModifyResult.NORMALIZATION_FAILURE);
            return;
        }

        if (userEntity == null || (!userEntity.isAdmin() && !userEntity.getId().equals(modifyVo.getId()))) {
            modifyVo.setResult(ModifyResult.NOT_ALLOWED);
            return;
        }

        BoardEntity boardEntity = this.boardMapper.selectBoard(modifyVo);
        if (boardEntity == null) {
            modifyVo.setResult(ModifyResult.BOARD_NOT_DEFINED);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(modifyVo);
        if (articleEntity == null) {
            modifyVo.setResult(ModifyResult.ARTICLE_NOT_DEFINED);
            return;
        }
        modifyVo.setTitle(articleEntity.getTitle());
        modifyVo.setContent(articleEntity.getContent());
        modifyVo.setResult(ModifyResult.SUCCESS);
    }

    public void modifyByPost(ModifyVo modifyVo, UserEntity userEntity) {
        if (!BoardService.checkCode(modifyVo.getBoardCode()) ||
                !BoardService.checkArticleIndex(String.valueOf(modifyVo.getArticleIndex())) ||
                modifyVo.getTitle().length() == 0 ||
                modifyVo.getContent().length() == 0 ||
                modifyVo.getTitle().length() > 100 ||
                modifyVo.getContent().length() > 10000 ||
                modifyVo.getContent().replaceAll(" ", "").contains("<script")) {
            modifyVo.setResult(ModifyResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null || (!userEntity.isAdmin() && !modifyVo.getId().equals(userEntity.getId()))) {
            modifyVo.setResult(ModifyResult.NOT_ALLOWED);
            return;
        }
        this.boardMapper.updateArticle(modifyVo);
    }
}
