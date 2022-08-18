package com.example.pd.board.vo;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.enums.DeleteResult;
import com.example.pd.interfaces.IResult;

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
