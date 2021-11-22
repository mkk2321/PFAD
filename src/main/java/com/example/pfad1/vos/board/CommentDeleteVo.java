package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.CommentEntity;
import com.example.pfad1.enums.board.CommentDeleteResult;
import com.example.pfad1.interfaces.IResult;

public class CommentDeleteVo extends CommentEntity implements IResult<CommentDeleteResult> {
    private CommentDeleteResult result;

    @Override
    public CommentDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(CommentDeleteResult result) {
        this.result = result;
    }
}
