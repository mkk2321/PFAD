package com.example.pd.board.vo;

import com.example.pd.board.entity.CommentEntity;
import com.example.pd.board.enums.CommentWriteResult;
import com.example.pd.interfaces.IBoard;
import com.example.pd.interfaces.IResult;

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
