package com.example.pfad1.services;

import com.example.pfad1.enums.board.*;
import com.example.pfad1.vos.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.mappers.IBoardMapper;

@Service
public class BoardService {
    private static class Config {
        public final static int ARTICLE_COUNT_PER_PAGE = 10;
        public final static int PAGING_NUMBER = 10;
        private Config() {

        }
    }
    private static class RegExp {
        public static final String CODE = "^([a-z]{2,50})$";
        public static final String PAGE = "^([1-9]([0-9]{0,3})?)$";
        public static final String ARTICLE_INDEX = "^([1-9]([0-9]{0,5})?)$";
        private RegExp(){

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

    public static boolean checkArticleIndex(String s) { return s != null && s.matches(RegExp.ARTICLE_INDEX); }

    public void list(ListVo listVo) {
        if(!BoardService.checkCode(listVo.getCode()) ||
        !BoardService.checkPage(String.valueOf(listVo.getPage()))) {
            listVo.setResult(ListResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(listVo);
        if(boardEntity == null) {
            listVo.setResult(ListResult.BOARD_NOT_DEFINED);
            return;
        }
        int boardPerPage = this.boardMapper.selectArticleCount(listVo);
        listVo.calcMaxPage(boardPerPage, Config.ARTICLE_COUNT_PER_PAGE);
        listVo.calcStartEndPage(listVo.getPage(), Config.PAGING_NUMBER);
        listVo.setBoardPerCount(boardPerPage);
        listVo.setName(boardEntity.getName());
        listVo.setCode(boardEntity.getCode());
        listVo.setWriteForbidden(boardEntity.isWriteForbidden());
        listVo.setQueryLimit(Config.ARTICLE_COUNT_PER_PAGE);
        listVo.setQueryOffset((listVo.getPage()-1) * Config.ARTICLE_COUNT_PER_PAGE);
        listVo.setArticles(this.boardMapper.selectArticlesByList(listVo));
        listVo.setResult(ListResult.SUCCESS);
    }

    public void read(ReadVo readVo, UserEntity userEntity) {
        if(!BoardService.checkArticleIndex(String.valueOf(readVo.getIndex()))){
            readVo.setResult(ReadResult.NORMALIZATION_FAILURE);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(readVo);
        if(articleEntity == null || articleEntity.isDeleted()) {
            readVo.setResult(ReadResult.ARTICLE_NOT_DEFINED);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(articleEntity);
        if(boardEntity.isReadForbidden() && (userEntity == null || !userEntity.isAdmin())) {
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
        readVo.setResult(ReadResult.SUCCESS);
    }

    public void deleteArticle(UserEntity userEntity, DeleteVo deleteVo) {
        if(!BoardService.checkArticleIndex(String.valueOf(deleteVo.getIndex()))) {
            deleteVo.setResult(DeleteResult.NORMALIZATION_FAILURE);
            return;
        }
        ArticleEntity articleEntity = this.boardMapper.selectArticle(deleteVo);
        if(articleEntity == null) {
            deleteVo.setResult(DeleteResult.FAILURE);
            return;
        }
        if(userEntity == null ||
                (!userEntity.isAdmin() && !userEntity.getId().equals(deleteVo.getId()))) {
            deleteVo.setResult(DeleteResult.NOT_ALLOWED);
            return;
        }
        this.boardMapper.deleteArticle(deleteVo);
        deleteVo.setBoardCode(articleEntity.getBoardCode());
        deleteVo.setResult(DeleteResult.SUCCESS);
    }
    public void writeByGet(WriteVo writeVo, UserEntity userEntity) {
        if(!BoardService.checkCode(writeVo.getBoardCode())) {
            writeVo.setResult(WriteResult.NORMALIZATION_FAILURE);
            return;
        }
        BoardEntity boardEntity = this.boardMapper.selectBoard(writeVo);
        if(boardEntity == null) {
            writeVo.setResult(WriteResult.BOARD_NOT_DEFINED);
            return;
        }
        if(userEntity == null || (boardEntity.isWriteForbidden() && !userEntity.isAdmin())) {
            writeVo.setResult(WriteResult.WRITE_NOT_ALLOWED);
            return;
        }
        writeVo.setResult(WriteResult.SUCCESS);
    }

    public void uploadImage(ImageUploadVo imageUploadVo, UserEntity userEntity) {
        if(userEntity == null) {
            imageUploadVo.setResult(ImageUploadResult.NOT_ALLOWED);
            return;
        }
//        TODO: image upload..
    }

}
