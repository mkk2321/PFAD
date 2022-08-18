package com.example.pd.user.vo;

import com.example.pd.interfaces.ICode;
import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.RegisterResult;
import com.example.pd.utils.CryptoUtil;

public class RegisterVo extends UserEntity implements IResult<RegisterResult>, ICode {
    private RegisterResult result;
    private String hashedPassword;
    private String verificationCode;

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public RegisterResult getResult() {
        return result;
    }

    @Override
    public void setResult(RegisterResult result) {
        this.result = result;
    }

    @Override
    public String getCode() {
        return verificationCode;
    }
}
