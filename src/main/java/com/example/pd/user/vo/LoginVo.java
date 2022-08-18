package com.example.pd.user.vo;

import com.example.pd.interfaces.IResult;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.LoginResult;
import com.example.pd.utils.CryptoUtil;

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
