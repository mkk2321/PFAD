package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.PasswordRecoverResult;
import com.example.pfad1.interfaces.ICode;
import com.example.pfad1.interfaces.IResult;

public class PasswordRecoverVo extends UserEntity implements IResult<PasswordRecoverResult>, ICode {
    private PasswordRecoverResult result;
    private String passwordVerificationCode;

    public void setPasswordVerificationCode(String passwordVerificationCode) {
        this.passwordVerificationCode = passwordVerificationCode;
    }

    @Override
    public String getCode() {
        return passwordVerificationCode;
    }

    @Override
    public PasswordRecoverResult getResult() {
        return result;
    }

    @Override
    public void setResult(PasswordRecoverResult result) {
        this.result = result;
    }
}
