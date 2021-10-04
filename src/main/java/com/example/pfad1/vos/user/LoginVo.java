package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.LoginResult;
import com.example.pfad1.interfaces.IResult;
import com.example.pfad1.utils.CryptoUtil;

public class LoginVo extends UserEntity implements IResult<LoginResult> {
    private LoginResult result;
    private String hashedPassword;
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.hashedPassword = CryptoUtil.Sha512.hash(password);
    }

    @Override
    public LoginResult getResult() {
        return result;
    }

    @Override
    public void setResult(LoginResult result) {
        this.result = result;
    }
}
