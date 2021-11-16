package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.enums.board.WriteResult;
import com.example.pfad1.interfaces.IResult;

public class WriteVo extends ArticleEntity implements IResult<WriteResult> {
    private WriteResult result;

    @Override
    public WriteResult getResult() {
        return result;
    }

    @Override
    public void setResult(WriteResult result) {
        this.result = result;
    }
}
