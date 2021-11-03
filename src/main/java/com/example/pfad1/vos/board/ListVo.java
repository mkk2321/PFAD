package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.entities.board.BoardEntity;
import com.example.pfad1.enums.board.ListResult;
import com.example.pfad1.interfaces.IResult;

public class ListVo extends BoardEntity implements IResult<ListResult> {
    private ListResult result;
    private int page;
    private ArticleEntity[] articles;

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
}
