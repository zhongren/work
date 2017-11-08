package com.admin.controller.sys;

import com.admin.common.base.ParamBean;
import com.admin.controller.BaseController;
import com.admin.service.sys.UserService;
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
        return success(userService.findPage(paramBean), "分页查询");
    }
}
