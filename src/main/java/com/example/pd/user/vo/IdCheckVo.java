package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.IdCheckResult;

public class IdCheckVo extends UserEntity implements IResult<IdCheckResult> {
    private IdCheckResult result;

    @Override
    public IdCheckResult getResult() {
        return result;
    }

    @Override
    public void setResult(IdCheckResult result) {
        this.result = result;
    }
}
