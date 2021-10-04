package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.IdRecoverResult;
import com.example.pfad1.interfaces.IResult;

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
