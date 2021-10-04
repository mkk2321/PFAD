package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.enums.board.ListResult;
import com.example.pfad1.interfaces.IResult;

public class ListVo extends BoardEntity implements IResult<ListResult> {
    private int page;
    private ListResult result;
    private ArticleEntity[] articles;
    private int startPage;
    private int endPage;
    private int maxPage;
    private int queryLimit;
    private int queryOffset;

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

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
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

    @Override
    public ListResult getResult() {
        return result;
    }

    @Override
    public void setResult(ListResult result) {
        this.result = result;
    }


}
