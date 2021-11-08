package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.enums.board.ReadResult;
import com.example.pfad1.interfaces.IResult;

public class ReadVo extends ArticleEntity implements IResult<ReadResult> {
    private ReadResult result;

    @Override
    public ReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ReadResult result) {
        this.result = result;
    }
}
