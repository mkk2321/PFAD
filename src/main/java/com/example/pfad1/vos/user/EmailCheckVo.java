package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.EmailCheckResult;
import com.example.pfad1.interfaces.IResult;

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
