package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.VerificationCodeEntity;
import com.example.pd.user.enums.PasswordRecoverResult;

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
