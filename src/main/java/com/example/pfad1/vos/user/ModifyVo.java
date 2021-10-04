package com.example.pfad1.vos.user;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.ModifyResult;
import com.example.pfad1.interfaces.IResult;
import com.example.pfad1.utils.CryptoUtil;

public class ModifyVo extends UserEntity implements IResult<ModifyResult> {
    private ModifyResult result;
    private String hashedPassword;
    private String checkPassword;

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
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
    public ModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(ModifyResult result) {
        this.result = result;
    }
}
