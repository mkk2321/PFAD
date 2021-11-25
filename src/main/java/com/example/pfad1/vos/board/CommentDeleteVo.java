package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.CommentEntity;
import com.example.pfad1.enums.board.CommentDeleteResult;
import com.example.pfad1.interfaces.IBoard;
import com.example.pfad1.interfaces.IResult;

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
