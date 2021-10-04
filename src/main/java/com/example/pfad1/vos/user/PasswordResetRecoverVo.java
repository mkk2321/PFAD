package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.VerificationCodeEntity;
import com.example.pfad1.enums.user.PasswordResetRecoverResult;
import com.example.pfad1.interfaces.IResult;
import com.example.pfad1.utils.CryptoUtil;

public class PasswordResetRecoverVo extends VerificationCodeEntity implements IResult<PasswordResetRecoverResult> {
    private PasswordResetRecoverResult result;
    private String password;
    private String checkPassword;
    private String hashedPassword;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    @Override
    public PasswordResetRecoverResult getResult() {
        return result;
    }

    @Override
    public void setResult(PasswordResetRecoverResult result) {
        this.result = result;
    }
}
