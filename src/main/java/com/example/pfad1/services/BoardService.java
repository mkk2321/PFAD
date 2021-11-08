package com.example.pfad1.services;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.board.ListResult;
import com.example.pfad1.enums.board.ReadResult;
import com.example.pfad1.mappers.IBoardMapper;
import com.example.pfad1.vos.board.ListVo;
import com.example.pfad1.vos.board.ReadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        BoardEntity boardEntity = this.boardMapper.selectBoard(readVo);
        if(boardEntity.isReadForbidden() && (userEntity == null || !userEntity.isAdmin())) {
            readVo.setResult(ReadResult.READ_NOT_ALLOWED);
            return;
        }
        readVo.setId(articleEntity.getId());
        readVo.setBoardCode(articleEntity.getBoardCode());
        readVo.setCreatedAt(articleEntity.getCreatedAt());
        readVo.setUpdatedAt(articleEntity.getUpdatedAt());
        readVo.setTitle(articleEntity.getTitle());
        readVo.setContent(articleEntity.getContent());
        readVo.setView(articleEntity.getView());
        readVo.setDeleted(articleEntity.isDeleted());
        readVo.setResult(ReadResult.SUCCESS);
    }
}
