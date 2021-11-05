package com.example.pfad1.entities.board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleEntity {
    protected int index;
    protected String id;
    protected String boardCode;
    protected Date createdAt;
    protected Date updatedAt;
    protected String title;
    protected String content;
    protected int view;
    protected boolean isDeleted;

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
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.createdAt);
    }

    public String formatUpdatedAt(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.updatedAt);
    }
}
