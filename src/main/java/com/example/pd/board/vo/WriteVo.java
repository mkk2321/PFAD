package com.example.pd.board.vo;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.enums.WriteResult;
import com.example.pd.interfaces.IResult;

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
