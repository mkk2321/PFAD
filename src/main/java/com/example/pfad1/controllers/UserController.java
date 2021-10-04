package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.user.LoginResult;
import com.example.pfad1.enums.user.PasswordRecoverResult;
import com.example.pfad1.services.UserService;
import com.example.pfad1.vos.user.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller("com.example.pfad1.controllers")
@RequestMapping(value = "/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/check-email",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkEmailPost(
            @RequestBody EmailCheckVo emailCheckVo) {
        this.userService.checkEmail(emailCheckVo);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", emailCheckVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/check-id",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String checkIdPost(
            @RequestBody IdCheckVo idCheckVo) {
        this.userService.checkId(idCheckVo);

        JSONObject responseJson = new JSONObject();
        responseJson.put("result", idCheckVo.getResult().name().toLowerCase());
        return responseJson.toString();
    }


    @RequestMapping(value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet() {
        return "user/login";
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginPost(LoginVo loginVo,
                            HttpSession session,
                            Model model) {
        this.userService.login(loginVo);
        if (loginVo.getResult() == LoginResult.SUCCESS) {
            session.setAttribute("userEntity", loginVo.getUserEntity());
            return "redirect:/";
        }
        model.addAttribute("loginResult", loginVo.getResult());
        return "user/login";
    }

    @RequestMapping(value = "logout",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String logoutGet(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet() {
        return "user/register";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost(
            RegisterVo registerVo,
            Model model) throws MessagingException {
        this.userService.register(registerVo);
        model.addAttribute("registerResult", registerVo.getResult());
        return "user/register";
    }

    @RequestMapping(value = "/verify-email",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String verifyEmailGet(EmailVerificationVo emailVerificationVo,
                                 Model model) {
        this.userService.verifyEmail(emailVerificationVo);
        model.addAttribute("emailVerificationResult", emailVerificationVo.getResult());
        return "user/verifyEmail";
    }

    @RequestMapping(value = "/recover",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String recoverGet() {
        return "user/recover";
    }

    @RequestMapping(value = "/recover-id",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String RecoverId(
            IdRecoverVo idRecoverVo,
            Model model) {
        this.userService.recoverEmail(idRecoverVo);
        model.addAttribute("idRecoverVo", idRecoverVo);
        return "user/recoverId";
    }

    @RequestMapping(value = "/recover-password",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String RecoverPasswordPost(
            PasswordRecoverVo passwordRecoverVo,
            Model model) throws MessagingException {
        this.userService.recoverPassword(passwordRecoverVo);
        model.addAttribute("passwordRecoverResult", passwordRecoverVo.getResult());
        return "user/recoverPassword";
    }

    @RequestMapping(value = "/verify-password-recover",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String verifyPasswordRecoverGet(
            PasswordRecoverVerificationVo passwordRecoverVerificationVo,
            Model model) {
        this.userService.verifyRecoverPassword(passwordRecoverVerificationVo);
        model.addAttribute("passwordRecoverResult", passwordRecoverVerificationVo.getResult());
        if (passwordRecoverVerificationVo.getResult() == PasswordRecoverResult.SUCCESS) {
            return "redirect:/recover-password-reset?code=" + passwordRecoverVerificationVo.getCode();
        }
        return "user/verifyPasswordRecover";
    }

    @RequestMapping(value = "/recover-password-reset",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String recoverPasswordResetGet(
            PasswordResetRecoverVo passwordResetRecoverVo,
            Model model) {
        this.userService.recoverPasswordResetGet(passwordResetRecoverVo);
        model.addAttribute("passwordResetRecoverVo", passwordResetRecoverVo);
        return "/user/recoverPasswordReset";
    }

    @RequestMapping(value = "/recover-password-reset",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String recoverPasswordModifyPost(
            PasswordResetRecoverVo passwordResetRecoverVo,
            Model model) {
        this.userService.recoverPasswordReset(passwordResetRecoverVo);
        model.addAttribute("passwordResetRecoverVo", passwordResetRecoverVo);
        return "user/recoverPasswordReset";

    }

    @RequestMapping(value = "/modify-password-check",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPasswordCheckGet(
            @SessionAttribute(value = "userEntity", required = false) UserEntity userEntity) {
        if (userEntity == null) {
            return "redirect:/";
        }
        return "user/modifyPasswordCheck";
    }

    @RequestMapping(value = "/modify-password-check",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPasswordCheckPost(
            @SessionAttribute(value = "userEntity") UserEntity userEntity,
            ModifyVo modifyVo,
            Model model,
            HttpSession session) {
        if(userEntity == null) {
            return "redirect:/";
        }
        session.getAttribute("userEntity");
        this.userService.modifyPasswordCheck(userEntity, modifyVo);
        model.addAttribute("modifyResult", modifyVo.getResult());
        return "user/modifyPasswordCheck";
    }

    @RequestMapping(value = "modify",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyGet(
            @SessionAttribute(value = "userEntity", required = false) UserEntity userEntity) {
        if(userEntity == null) {
            return "redirect:/";
        }
        return "/user/modify";
    }

    @RequestMapping(value = "/modify",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPost(ModifyVo modifyVo,
                             @SessionAttribute(value = "userEntity") UserEntity userEntity,
                             HttpSession session,
                             Model model) {
        if(userEntity == null) {
            return "redirect:/";
        }
        session.getAttribute("userEntity");
        this.userService.modify(userEntity, modifyVo);
        System.out.println(modifyVo.getResult());
        model.addAttribute("modifyResult", modifyVo.getResult());
        return "user/modify";
    }

}