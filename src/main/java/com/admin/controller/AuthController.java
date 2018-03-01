package com.admin.controller;

import com.admin.common.base.BaseController;
import com.admin.common.bean.ParamBean;
import com.admin.common.bean.ResultBean;
import com.admin.common.exception.AuthException;
import com.admin.common.exception.enums.AuthEnum;
import com.admin.model.user.UserParamVo;
import com.admin.model.user.UserVo;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ZR_a on 2017/12/2.
 */
@RestController
@RequestMapping("auth")
public class AuthController extends BaseController{

    @RequestMapping(value = "login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBean login(@RequestBody UserParamVo userParamVo) {
        String account = userParamVo.getAccount();
        String password=userParamVo.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
            UserVo user = (UserVo) subject.getPrincipal();
            return success(user,"登陆成功");
        } catch (UnknownAccountException e) {
            throw new AuthException(AuthEnum.UNKNOWN_ACCOUNT);
        } catch (DisabledAccountException e) {
            throw new AuthException(AuthEnum.DISABLED_ACCOUNT);
        } catch (Exception e) {
            e.printStackTrace();
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
