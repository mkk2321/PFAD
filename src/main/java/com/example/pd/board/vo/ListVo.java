package com.example.pd.board.vo;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.entity.BoardEntity;
import com.example.pd.board.enums.ListResult;
import com.example.pd.interfaces.IResult;

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
    private String criteria;
    private String keyword;
    private String searchUrl;

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getKeywordWithoutSpaces() {
        return this.keyword.replace(" ", "");
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

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

    public boolean isSearching(){
        return this.criteria != null && this.keyword != null;
    }
}
