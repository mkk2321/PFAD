package com.example.pfad1.services;

import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.enums.board.ListResult;
import com.example.pfad1.mappers.IBoardMapper;
import com.example.pfad1.vos.board.ListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private static class Config {
        public static final int ARTICLE_COUNT_PER_PAGE = 10;
        public static final int PAGING_NUMBER = 5;
        // TODO: 2021 08 18 20 18 11 read페이지 만들기
        // TODO: 상품 등록 기능 완성하기 - jsp만 만들어 놓은 상태

        private Config() {

        }
    }

    private static class RegExp {
        public static final String CODE = "^([a-z]{2,50})$";
        public static final String PAGE = "^([1-9]([0-9]{0,3})?)$";

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
        int articleCount = this.boardMapper.selectArticleCount(listVo);
        int maxPage = articleCount / Config.ARTICLE_COUNT_PER_PAGE +
                (articleCount % Config.ARTICLE_COUNT_PER_PAGE == 0 ? 0 : 1);
        int startPage = ((listVo.getPage() / Config.PAGING_NUMBER) * Config.PAGING_NUMBER + (listVo.getPage() % Config.PAGING_NUMBER == 0 ? (-Config.PAGING_NUMBER + 1) : 1));
        int endPage = Math.min(startPage + (Config.PAGING_NUMBER - 1), maxPage);
        listVo.setMaxPage(maxPage);
        if (listVo.getPage() > maxPage) {
            listVo.setPage(maxPage);
        }

        listVo.setStartPage(startPage);
        listVo.setEndPage(endPage);
        listVo.setQueryLimit(Config.ARTICLE_COUNT_PER_PAGE);
        listVo.setQueryOffset((listVo.getPage() - 1) * Config.ARTICLE_COUNT_PER_PAGE);
        listVo.setName(boardEntity.getName());
        listVo.setArticles(this.boardMapper.selectArticlesByList(listVo));
        listVo.setResult(ListResult.SUCCESS);

    }
}
