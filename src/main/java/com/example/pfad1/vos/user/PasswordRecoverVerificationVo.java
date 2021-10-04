package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.VerificationCodeEntity;
import com.example.pfad1.enums.user.PasswordRecoverResult;
import com.example.pfad1.interfaces.IResult;

public class PasswordRecoverVerificationVo extends VerificationCodeEntity implements IResult<PasswordRecoverResult> {
    private PasswordRecoverResult result;

    @Override
    public PasswordRecoverResult getResult() {
        return result;
    }

    @Override
    public void setResult(PasswordRecoverResult result) {
        this.result = result;
    }
}
