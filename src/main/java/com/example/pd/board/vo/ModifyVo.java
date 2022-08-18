package com.example.pd.board.vo;

import com.example.pd.board.entity.ArticleEntity;
import com.example.pd.board.enums.ModifyResult;
import com.example.pd.interfaces.IResult;

public class ModifyVo extends ArticleEntity implements IResult<ModifyResult> {
    private ModifyResult result;

    @Override
    public ModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(ModifyResult result) {
        this.result = result;
    }
}
