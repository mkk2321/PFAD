package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.EmailCheckResult;

public class EmailCheckVo extends UserEntity implements IResult<EmailCheckResult> {
    private EmailCheckResult result;

    @Override
    public EmailCheckResult getResult() {
        return result;
    }

    @Override
    public void setResult(EmailCheckResult result) {
        this.result = result;
    }
}
