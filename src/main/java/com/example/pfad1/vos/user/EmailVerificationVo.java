package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.VerificationCodeEntity;
import com.example.pfad1.enums.user.EmailVerificationResult;
import com.example.pfad1.interfaces.IResult;

public class EmailVerificationVo extends VerificationCodeEntity implements IResult<EmailVerificationResult> {
    private EmailVerificationResult result;

    @Override
    public EmailVerificationResult getResult() {
        return result;
    }

    @Override
    public void setResult(EmailVerificationResult result) {
        this.result = result;
    }
}
