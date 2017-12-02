package com.admin.controller;

import com.admin.common.base.BaseController;
import com.admin.common.bean.ParamBean;
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

    @RequestMapping(value = "doLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String doLogin() {
        System.out.println("登陆成功");
        return success("登陆成功");
    }
}
