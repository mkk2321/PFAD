package com.example.pd.user.vo;

import com.example.pd.interfaces.ICode;
import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.PasswordRecoverResult;

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
