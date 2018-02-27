package com.admin.controller;

import com.admin.common.base.BaseController;
import com.admin.common.bean.ParamBean;
import com.admin.common.exception.AuthException;
import com.admin.common.exception.enums.AuthEnum;
import com.admin.model.user.UserVo;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZR_a on 2017/12/2.
 */
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController{

    @RequestMapping(value = "login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String login() {
        ParamBean paramModel = getParamBean();
        String account = (String) paramModel.get("account");
        String password = (String) paramModel.get("password");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);

        try {
            subject.login(token);
            UserVo user = (UserVo) subject.getPrincipal();
            //登录成功的话,返回登录的用户名
            return success(user.getAccount(),"登陆成功");
        } catch (UnknownAccountException e) {
            throw new AuthException(AuthEnum.UNKNOWN_ACCOUNT);
        } catch (DisabledAccountException e) {
            throw new AuthException(AuthEnum.DISABLED_ACCOUNT);
        } catch (Exception e) {
            throw new AuthException(AuthEnum.WRONG_PASSWORD);
        }

    }

    @RequestMapping(value = "noAuth")
    public String noAuth() {
        /*
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            throw new AuthException(AuthEnum.WRONG_PASSWORD);
        }
       */
        throw new AuthException(AuthEnum.UNAUTHORIZED);
    }

    @RequestMapping(value = "noLogin")
    public String noLogin() {
        throw new AuthException(AuthEnum.UNLOGIN);
    }
}
