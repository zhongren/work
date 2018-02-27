package com.admin.controller;

import com.admin.common.bean.ParamBean;
import com.admin.common.base.BaseController;
import com.admin.model.user.UserVo;
import com.admin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String page() {
        ParamBean paramBean = getParamBean();
        return success(userService.page(paramBean), "分页查询");
    }

    @RequestMapping(value = "create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String create() {
        UserVo userVo=new UserVo();
        userVo.setAccount("ccc");
        return success(userService.create(userVo), "添加");
    }
}
