package com.example.pfad1.mappers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.entities.user.VerificationCodeEntity;
import com.example.pfad1.interfaces.ICode;
import com.example.pfad1.vos.user.IdRecoverVo;
import com.example.pfad1.vos.user.PasswordRecoverVo;
import com.example.pfad1.vos.user.RegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserMapper {
    int selectEmailCount(@Param("email") String email);
    int selectIdCount(@Param("id") String id);
    UserEntity selectUserByVerificationCode(VerificationCodeEntity verificationCodeEntity);
    UserEntity selectUser(UserEntity userEntity);
    int insertUser(RegisterVo registerVo);
    int insertVerificationCode(ICode code);
    int updateUser(UserEntity userEntity);
    int deleteEmailVerification(VerificationCodeEntity verificationCodeEntity);

    UserEntity selectUserByIdRecover(IdRecoverVo idRecoverVo);
    UserEntity selectUserByPasswordRecover(PasswordRecoverVo passwordRecoverVo);
}
