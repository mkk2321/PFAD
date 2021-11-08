package com.example.pfad1.entities.board;

import com.example.pfad1.interfaces.IBoard;

public class BoardEntity implements IBoard {
    protected String code;
    protected String name;
    protected boolean isReadForbidden;
    protected boolean isWriteForbidden;
    protected boolean isCommentForbidden;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReadForbidden() {
        return isReadForbidden;
    }

    public void setReadForbidden(boolean readForbidden) {
        isReadForbidden = readForbidden;
    }

    public boolean isWriteForbidden() {
        return isWriteForbidden;
    }

    public void setWriteForbidden(boolean writeForbidden) {
        isWriteForbidden = writeForbidden;
    }

    public boolean isCommentForbidden() {
        return isCommentForbidden;
    }

    public void setCommentForbidden(boolean commentForbidden) {
        isCommentForbidden = commentForbidden;
    }

    @Override
    public String getBoardCode() {
        return this.code;
    }
}
