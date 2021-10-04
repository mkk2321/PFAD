package com.example.pfad1.services;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.*;
import com.example.pfad1.mappers.IUserMapper;
import com.example.pfad1.utils.CryptoUtil;
import com.example.pfad1.vos.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UserService {

    public static class RegRex {
        public static final String ID = "^([a-zA-Z0-9가-힣]){2,20}$";
        public static final String EMAIL = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        public static final String NAME = "^([가-힣]{2,4})$";
        public static final String BIRTH = "^([0-9]{6})$";
        public static final String GENDER = "^([0-4])$";
        public static final String CONTACT_COMPANY = "^(skt|kt|lgu|etc)$";
        public static final String CONTACT_FIRST = "^([0-9]{3})$";
        public static final String CONTACT_SECOND = "^([0-9]{4})$";
        public static final String CONTACT_THIRD = "^([0-9]{4})$";
        public static final String ADDRESS_POSTAL = "^([0-9]{5})$";
        public static final String ADDRESS_PRIMARY = "^([가-힣a-zA-Z0-9\\-\\.\\(\\) ]{1,})$";
        public static final String ADDRESS_SECONDARY = "^([가-힣a-zA-Z0-9\\-\\.\\(\\) ]{0,})$";

        public static final String EMAIL_VERIFICATION_CODE = "^([a-zA-Z0-9]{128})$";
        public static final String PASSWORD_RECOVER_VERIFICATION_CODE = "^([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\_\\=\\+\\\\\\|\\[\\]\\{\\}\\'\\\"\\;\\:\\,\\.\\/\\?\\<\\>\\`\\~ ]{128})$";

        private RegRex() {
        }
    }

    private final IUserMapper userMapper;
    private final JavaMailSender mailSender;

    @Autowired
    public UserService(IUserMapper userMapper, JavaMailSender mailSender) {
        this.userMapper = userMapper;
        this.mailSender = mailSender;
    }

    public static boolean checkEmail(String s) {
        return s != null && s.matches(RegRex.EMAIL);
    }

    public static boolean checkPassword(String s) {
        return s != null && s.matches(RegRex.PASSWORD);
    }

    public static boolean checkId(String s) {
        return s != null && s.matches(RegRex.ID);
    }

    public static boolean checkName(String s) {
        return s != null && s.matches(RegRex.NAME);
    }

    public static boolean checkBirth(String s) {
        return s != null && s.matches(RegRex.BIRTH);
    }

    public static boolean checkGender(String s) {
        return s != null && s.matches(RegRex.GENDER);
    }

    public static boolean checkContactCompany(String s) {
        return s != null && s.matches(RegRex.CONTACT_COMPANY);
    }

    public static boolean checkContactFirst(String s) {
        return s != null && s.matches(RegRex.CONTACT_FIRST);
    }

    public static boolean checkContactSecond(String s) {
        return s != null && s.matches(RegRex.CONTACT_SECOND);
    }

    public static boolean checkContactThird(String s) {
        return s != null && s.matches(RegRex.CONTACT_THIRD);
    }

    public static boolean checkAddressPostal(String s) {
        return s != null && s.matches(RegRex.ADDRESS_POSTAL);
    }

    public static boolean checkAddressPrimary(String s) {
        return s != null && s.matches(RegRex.ADDRESS_PRIMARY);
    }

    public static boolean checkAddressSecondary(String s) {
        return s != null && s.matches(RegRex.ADDRESS_SECONDARY);
    }

    public static boolean checkEmailVerificationCode(String s) {
        return s != null && s.matches(RegRex.EMAIL_VERIFICATION_CODE);
    }

    public static boolean checkPasswordRecoverVerificationCode(String s) {
        return s != null && s.matches(RegRex.PASSWORD_RECOVER_VERIFICATION_CODE);
    }

    public void checkEmail(EmailCheckVo emailCheckVo) {
        if (!UserService.checkEmail(emailCheckVo.getEmail())) {
            emailCheckVo.setResult(EmailCheckResult.NORMALIZATION_FAILURE);
            return;
        }
        if (this.userMapper.selectEmailCount(emailCheckVo.getEmail()) > 0) {
            emailCheckVo.setResult(EmailCheckResult.EXISTING);
            return;
        }
        emailCheckVo.setResult(EmailCheckResult.NON_EXISTING);
    }

    public void checkId(IdCheckVo idCheckVo) {
        if (!UserService.checkId(idCheckVo.getId())) {
            idCheckVo.setResult(IdCheckResult.NORMALIZATION_FAILURE);
            return;
        }
        if (this.userMapper.selectIdCount(idCheckVo.getId()) > 0) {
            idCheckVo.setResult(IdCheckResult.EXISTING);
            return;
        }
        idCheckVo.setResult(IdCheckResult.NON_EXISTING);
    }

    public void login(LoginVo loginVo) {
        if (!UserService.checkId(loginVo.getId()) ||
                !UserService.checkPassword(loginVo.getPassword())) {
            loginVo.setResult(LoginResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUser(loginVo);
        if (userEntity == null) {
            loginVo.setResult(LoginResult.FAILURE);
            return;
        }

        if (!userEntity.isEmailVerified()) {
            loginVo.setResult(LoginResult.EMAIL_VERIFIED_FAILURE);
            return;
        }

        if (userEntity.isSuspended()) {
            loginVo.setResult(LoginResult.SUSPENDED_FAILURE);
            return;
        }

        if (userEntity.isDeleted()) {
            loginVo.setResult(LoginResult.DELETE_FAILURE);
            return;
        }
        loginVo.setUserEntity(userEntity);
        loginVo.setResult(LoginResult.SUCCESS);

    }

    public void register(RegisterVo registerVo) throws MessagingException {
        if (!UserService.checkEmail(registerVo.getEmail()) ||
                !UserService.checkPassword(registerVo.getPassword()) ||
                !UserService.checkId(registerVo.getId()) ||
                !UserService.checkName(registerVo.getName()) ||
                !UserService.checkBirth(registerVo.getBirth()) ||
                !UserService.checkGender(String.valueOf(registerVo.getGender())) ||
                !UserService.checkContactCompany(registerVo.getContactCompany()) ||
                !UserService.checkContactFirst(registerVo.getContactFirst()) ||
                !UserService.checkContactSecond(registerVo.getContactSecond()) ||
                !UserService.checkContactThird(registerVo.getContactThird()) ||
                !UserService.checkAddressPostal(registerVo.getAddressPostal()) ||
                !UserService.checkAddressPrimary(registerVo.getAddressPrimary()) ||
                !UserService.checkAddressSecondary(registerVo.getAddressSecondary())) {
            registerVo.setResult(RegisterResult.NORMALIZATION_FAILURE);
            return;
        }
        if (this.userMapper.selectEmailCount(registerVo.getEmail()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_EMAIL);
            return;
        }
        if (this.userMapper.selectIdCount(registerVo.getId()) > 0) {
            registerVo.setResult(RegisterResult.DUPLICATE_NICKNAME);
            return;
        }
        if (this.userMapper.insertUser(registerVo) == 0) {
            registerVo.setResult(RegisterResult.FAILURE);
            return;
        }
        String verificationCode = CryptoUtil.Sha512.hash(String.format("%s%s%f%f",
                registerVo.getEmail(),
                registerVo.getHashedPassword(),
                Math.random(),
                Math.random()));
        registerVo.setVerificationCode(verificationCode);
        this.userMapper.insertVerificationCode(registerVo);

        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(registerVo.getEmail());
        helper.setSubject("[PURE] 회원가입 인증 이메일");
        helper.setText(String.format("<a href=\"http://127.0.0.1/verify-email?code=%s\" target=\"_blank\" style=\"font-size: 1rem;\">인증하기</a>", registerVo.getCode()), true);
        this.mailSender.send(message);
        registerVo.setResult(RegisterResult.SUCCESS);
    }

    public void verifyEmail(EmailVerificationVo emailVerificationVo) {
        if (!UserService.checkEmailVerificationCode(emailVerificationVo.getCode())) {
            emailVerificationVo.setResult(EmailVerificationResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByVerificationCode(emailVerificationVo);
        if (userEntity == null) {
            emailVerificationVo.setResult(EmailVerificationResult.FAILURE);
            return;
        }
        userEntity.setEmailVerified(true);
        this.userMapper.updateUser(userEntity);
        this.userMapper.deleteEmailVerification(emailVerificationVo);
        emailVerificationVo.setResult(EmailVerificationResult.SUCCESS);
    }

    public void recoverEmail(IdRecoverVo idRecoverVo) {
        if (!UserService.checkEmail(idRecoverVo.getEmail()) ||
                !UserService.checkName(idRecoverVo.getName()) ||
                !UserService.checkBirth(idRecoverVo.getBirth()) ||
                !UserService.checkGender(String.valueOf(idRecoverVo.getGender())) ||
                !UserService.checkContactCompany(idRecoverVo.getContactCompany()) ||
                !UserService.checkContactFirst(idRecoverVo.getContactFirst()) ||
                !UserService.checkContactSecond(idRecoverVo.getContactSecond()) ||
                !UserService.checkContactThird(idRecoverVo.getContactThird())) {
            idRecoverVo.setResult(IdRecoverResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByIdRecover(idRecoverVo);
        if (userEntity == null) {
            idRecoverVo.setResult(IdRecoverResult.FAILURE);
            return;
        }
        idRecoverVo.setId(userEntity.getId());
        idRecoverVo.setResult(IdRecoverResult.SUCCESS);
    }

    public void recoverPassword(PasswordRecoverVo passwordRecoverVo) throws MessagingException {
        if (!UserService.checkId(passwordRecoverVo.getId()) ||
                !UserService.checkEmail(passwordRecoverVo.getEmail()) ||
                !UserService.checkName(passwordRecoverVo.getName()) ||
                !UserService.checkBirth(passwordRecoverVo.getBirth()) ||
                !UserService.checkGender(String.valueOf(passwordRecoverVo.getGender())) ||
                !UserService.checkContactCompany(passwordRecoverVo.getContactCompany()) ||
                !UserService.checkContactFirst(passwordRecoverVo.getContactFirst()) ||
                !UserService.checkContactSecond(passwordRecoverVo.getContactSecond()) ||
                !UserService.checkContactThird(passwordRecoverVo.getContactThird())) {
            passwordRecoverVo.setResult(PasswordRecoverResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByPasswordRecover(passwordRecoverVo);
        if (userEntity == null) {
            passwordRecoverVo.setResult(PasswordRecoverResult.FAILURE);
            return;
        }
        String emailVerificationCode = CryptoUtil.Sha512.hash(String.format("%s%s%f%f",
                passwordRecoverVo.getEmail(),
                passwordRecoverVo.getName(),
                Math.random(),
                Math.random()));
        passwordRecoverVo.setPasswordVerificationCode(emailVerificationCode);
        this.userMapper.insertVerificationCode(passwordRecoverVo);

        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(passwordRecoverVo.getEmail());
        helper.setSubject("[PURE] 비밀번호 찾기 인증 이메일");
        helper.setText(String.format("<a href=\"http://127.0.0.1/verify-password-recover?code=%s\" target=\"_blank\" style=\"font-size: 1rem;\">이메일 확인하기</a>", emailVerificationCode), true);
        this.mailSender.send(message);

        passwordRecoverVo.setResult(PasswordRecoverResult.SUCCESS);
    }

    public void verifyRecoverPassword(PasswordRecoverVerificationVo passwordRecoverVerificationVo) {
        if (!UserService.checkPasswordRecoverVerificationCode(passwordRecoverVerificationVo.getCode())) {
            passwordRecoverVerificationVo.setResult(PasswordRecoverResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByVerificationCode(passwordRecoverVerificationVo);
        if (userEntity == null) {
            passwordRecoverVerificationVo.setResult(PasswordRecoverResult.FAILURE);
            return;
        }
        passwordRecoverVerificationVo.setResult(PasswordRecoverResult.SUCCESS);

        return;
    }

    public void recoverPasswordResetGet(PasswordResetRecoverVo passwordResetRecoverVo) {
        UserEntity userEntity = this.userMapper.selectUserByVerificationCode(passwordResetRecoverVo);
        passwordResetRecoverVo.setId(userEntity.getId());
    }


    public void recoverPasswordReset(PasswordResetRecoverVo passwordResetRecoverVo) {
        if (!UserService.checkPasswordRecoverVerificationCode(passwordResetRecoverVo.getCode()) ||
                !UserService.checkPassword(passwordResetRecoverVo.getPassword()) ||
                !UserService.checkPassword(passwordResetRecoverVo.getCheckPassword())) {
            passwordResetRecoverVo.setResult(PasswordResetRecoverResult.NORMALIZATION_FAILURE);
            return;
        }
        UserEntity userEntity = this.userMapper.selectUserByVerificationCode(passwordResetRecoverVo);
        if (userEntity == null) {
            passwordResetRecoverVo.setResult(PasswordResetRecoverResult.FAILURE);
            return;
        }
        userEntity.setPassword(passwordResetRecoverVo.getHashedPassword());
        passwordResetRecoverVo.setId(userEntity.getId());
        this.userMapper.updateUser(userEntity);
        this.userMapper.deleteEmailVerification(passwordResetRecoverVo);
        passwordResetRecoverVo.setResult(PasswordResetRecoverResult.SUCCESS);
    }

    public void modifyPasswordCheck(UserEntity userEntity, ModifyVo modifyVo) {
        if (!UserService.checkPassword(modifyVo.getPassword())) {
            modifyVo.setResult(ModifyResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null || !userEntity.getPassword().equals(modifyVo.getHashedPassword())) {
            modifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        modifyVo.setResult(ModifyResult.SUCCESS);
    }

    public void modify(UserEntity userEntity, ModifyVo modifyVo) {
        if ((!UserService.checkPassword(modifyVo.getPassword()) && !modifyVo.getPassword().equals("")) ||
                (!UserService.checkPassword(modifyVo.getCheckPassword()) && !modifyVo.getCheckPassword().equals("")) ||
                !UserService.checkContactCompany(modifyVo.getContactCompany()) ||
                !UserService.checkContactFirst(modifyVo.getContactFirst()) ||
                !UserService.checkContactSecond(modifyVo.getContactSecond()) ||
                !UserService.checkContactThird(modifyVo.getContactThird()) ||
                !UserService.checkAddressPostal(modifyVo.getAddressPostal()) ||
                !UserService.checkAddressPrimary(modifyVo.getAddressPrimary()) ||
                !UserService.checkAddressSecondary(modifyVo.getAddressSecondary())) {
            modifyVo.setResult(ModifyResult.NORMALIZATION_FAILURE);
            return;
        }
        if (userEntity == null) {
            modifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        if (!modifyVo.getPassword().equals(modifyVo.getCheckPassword()) &&
                (modifyVo.getPassword() != null || modifyVo.getCheckPassword() != null)) {

            modifyVo.setResult(ModifyResult.FAILURE);
            return;
        }
        if (!modifyVo.getPassword().equals("")) {
            userEntity.setPassword(modifyVo.getHashedPassword());
        }
        userEntity.setContactCompany(modifyVo.getContactCompany());
        userEntity.setContactFirst(modifyVo.getContactFirst());
        userEntity.setContactSecond(modifyVo.getContactSecond());
        userEntity.setContactThird(modifyVo.getContactThird());
        userEntity.setAddressPostal(modifyVo.getAddressPostal());
        userEntity.setAddressPrimary(modifyVo.getAddressPrimary());
        userEntity.setAddressSecondary(modifyVo.getAddressSecondary());
        this.userMapper.updateUser(userEntity);
        modifyVo.setResult(ModifyResult.SUCCESS);
    }
}


