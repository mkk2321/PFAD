package com.example.pd.user.mapper;

import com.example.pd.interfaces.ICode;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.entity.VerificationCodeEntity;
import com.example.pd.user.vo.IdRecoverVo;
import com.example.pd.user.vo.PasswordRecoverVo;
import com.example.pd.user.vo.RegisterVo;

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
