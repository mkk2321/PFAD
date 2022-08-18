package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.IdRecoverResult;

public class IdRecoverVo extends UserEntity implements IResult<IdRecoverResult> {
    private IdRecoverResult result;

    @Override
    public IdRecoverResult getResult() {
        return result;
    }

    @Override
    public void setResult(IdRecoverResult result) {
        this.result = result;
    }
}
