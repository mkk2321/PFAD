package com.example.pfad1.vos.board;

import com.example.pfad1.entities.board.ArticleEntity;
import com.example.pfad1.enums.board.ModifyResult;
import com.example.pfad1.interfaces.IResult;

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
