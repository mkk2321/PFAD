package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.CommentEntity;
import com.example.pfad1.enums.board.CommentWriteResult;
import com.example.pfad1.interfaces.IBoard;
import com.example.pfad1.interfaces.IResult;

public class CommentWriteVo extends CommentEntity implements IBoard, IResult<CommentWriteResult> {
    private String boardCode;
    private CommentWriteResult result;

    @Override
    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    @Override
    public CommentWriteResult getResult() {
        return result;
    }

    @Override
    public void setResult(CommentWriteResult result) {
        this.result = result;
    }
}
