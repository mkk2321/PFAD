package com.example.pd.user.controller;

import com.example.pd.handler.models.ClientModel;
import com.example.pd.user.entity.UserEntity;
import com.example.pd.user.enums.LoginResult;
import com.example.pd.user.enums.PasswordRecoverResult;
import com.example.pd.user.service.UserService;
import com.example.pd.user.vo.EmailCheckVo;
import com.example.pd.user.vo.EmailVerificationVo;
import com.example.pd.user.vo.IdCheckVo;
import com.example.pd.user.vo.IdRecoverVo;
import com.example.pd.user.vo.LoginVo;
import com.example.pd.user.vo.ModifyVo;
import com.example.pd.user.vo.PasswordRecoverVerificationVo;
import com.example.pd.user.vo.PasswordRecoverVo;
import com.example.pd.user.vo.PasswordResetRecoverVo;
import com.example.pd.user.vo.RegisterVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@ComponentScan(basePackages = {"com.example.controllers"})
@Controller("com.example.pfad1.controllers")
//@RequestMapping(value = "/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/check-email", method = RequestMethod.POST)
    public Map<String, Object> checkEmailPost(EmailCheckVo emailCheckVo) {
    	
    	Map<String, Object> map = new HashMap<>();
		
    	this.userService.checkEmail(emailCheckVo);
    	
    	map.put("result", emailCheckVo.getResult());
    	
    	return map;
    }

    @ResponseBody
    @RequestMapping(value = "/check-id",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> checkIdPost(IdCheckVo idCheckVo) {
    	Map<String, Object> map = new HashMap<>();
        this.userService.checkId(idCheckVo);
        
        map.put("result", idCheckVo.getResult());
        
        return map;
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
			/* @RequestAttribute(value = "clientModel") ClientModel clientModel, */
            RegisterVo registerVo,
            Model model) throws MessagingException {
		this.userService.register(/* clientModel, */ registerVo);
        model.addAttribute("registerResult", registerVo.getResult());
        System.out.println("registerVo.getResult() : " + registerVo.getResult());
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
            //@RequestAttribute(value = "clientModel")ClientModel clientModel,
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
        if (userEntity == null) {
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
        if (userEntity == null) {
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
        if (userEntity == null) {
            return "redirect:/";
        }
        session.getAttribute("userEntity");
        this.userService.modify(userEntity, modifyVo);
        System.out.println(modifyVo.getResult());
        model.addAttribute("modifyResult", modifyVo.getResult());
        return "user/modify";
    }

}