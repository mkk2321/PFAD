package com.example.pfad1.entities.board;

import com.example.pfad1.interfaces.IArticle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentEntity implements IArticle {
    protected int index;
    protected String userId;
    protected int articleIndex;
    protected Date createdAt;
    protected String content;
    protected boolean isDeleted;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String formatCreatedAt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.createdAt);
    }
}
