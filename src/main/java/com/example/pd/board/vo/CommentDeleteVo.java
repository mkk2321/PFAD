package com.example.pd.board.vo;

import com.example.pd.board.entity.CommentEntity;
import com.example.pd.board.enums.CommentDeleteResult;
import com.example.pd.interfaces.IBoard;
import com.example.pd.interfaces.IResult;

public class CommentDeleteVo extends CommentEntity implements IBoard, IResult<CommentDeleteResult> {
    private CommentDeleteResult result;
    private String boardCode;

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    @Override
    public CommentDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(CommentDeleteResult result) {
        this.result = result;
    }

    @Override
    public String getBoardCode() {
        return boardCode;
    }
}
