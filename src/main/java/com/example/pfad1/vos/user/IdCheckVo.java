package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.IdCheckResult;
import com.example.pfad1.interfaces.IResult;

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
