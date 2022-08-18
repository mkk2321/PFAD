package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.VerificationCodeEntity;
import com.example.pd.user.enums.EmailVerificationResult;

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
