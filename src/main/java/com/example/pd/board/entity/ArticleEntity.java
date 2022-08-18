package com.example.pd.board.entity;

import com.example.pd.interfaces.IArticle;
import com.example.pd.interfaces.IBoard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleEntity implements IArticle, IBoard {
    protected int index;
    protected String id;
    protected String boardCode;
    protected Date createdAt;
    protected Date updatedAt;
    protected String title;
    protected String content;
    protected int view;
    protected boolean isDeleted;
    protected String name;
    protected int commentCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String formatCreatedAt(){
        return new SimpleDateFormat("yy-MM-dd HH:mm").format(this.createdAt);
    }

    public String formatUpdatedAt(){
        return new SimpleDateFormat("yy-MM-dd HH:mm").format(this.updatedAt);
    }

    @Override
    public int getArticleIndex() {
        return this.index;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
