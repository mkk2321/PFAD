package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.enums.board.ListResult;
import com.example.pfad1.interfaces.IResult;

public class ListVo extends BoardEntity implements IResult<ListResult> {
    private ListResult result;
    private int page;
    private ArticleEntity[] articles;
    private int boardPerCount;
    private int maxPage;
    private int endPage;
    private int startPage;
    private int queryLimit;
    private int queryOffset;

    public int getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(int queryLimit) {
        this.queryLimit = queryLimit;
    }

    public int getQueryOffset() {
        return queryOffset;
    }

    public void setQueryOffset(int queryOffset) {
        this.queryOffset = queryOffset;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getBoardPerCount() {
        return boardPerCount;
    }

    public void setBoardPerCount(int boardPerCount) {
        this.boardPerCount = boardPerCount;
    }

    public ArticleEntity[] getArticles() {
        return articles;
    }

    public void setArticles(ArticleEntity[] articles) {
        this.articles = articles;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public ListResult getResult() {
        return result;
    }

    @Override
    public void setResult(ListResult result) {
        this.result = result;
    }

    public void calcMaxPage(int boardPerCount, int articleCountPerPage) {
        this.setMaxPage((int) Math.ceil((double) boardPerCount / articleCountPerPage));
    }

    public void calcStartEndPage(int currentPage, int pagingNum) {
        this.setStartPage(((currentPage-1) / pagingNum) * pagingNum + 1);
        this.setEndPage(getStartPage() + pagingNum - 1);
        if(getMaxPage() < getEndPage()) {
            setEndPage(getMaxPage());
        }
    }
}
