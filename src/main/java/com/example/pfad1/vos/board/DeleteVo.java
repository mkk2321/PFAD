package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.enums.board.DeleteResult;
import com.example.pfad1.interfaces.IResult;

public class DeleteVo extends ArticleEntity implements IResult<DeleteResult> {
    private DeleteResult result;

    @Override
    public DeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(DeleteResult result) {
        this.result = result;
    }
}
