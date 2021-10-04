package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.RegisterResult;
import com.example.pfad1.interfaces.ICode;
import com.example.pfad1.interfaces.IResult;
import com.example.pfad1.utils.CryptoUtil;

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
