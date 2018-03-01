package com.admin.controller;

import com.admin.common.base.BaseController;
import com.admin.common.bean.ParamBean;
import com.admin.common.bean.ResultBean;
import com.admin.model.user.UserVo;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "findUserMenus",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean findUserMenus() {
        UserVo userVo=getLoginUser();

        return success(null, "分页查询");
    }


}
