package com.admin.controller;

import com.admin.common.base.BaseController;
import com.admin.common.bean.ParamBean;
import com.admin.common.bean.ResultBean;
import com.admin.common.util.StringUtil;
import com.admin.common.util.SysUtil;
import com.admin.model.sys.MenuVo;
import com.admin.model.user.UserVo;
import com.admin.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Autowired
    private SysService sysService;

    @RequestMapping(value = "userMenu",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResultBean userMenu() {
        Boolean build= StringUtil.getBoolean(getParam("build"));
        MenuVo menuVo=new MenuVo();
        Subject subject = SecurityUtils.getSubject();
        UserVo userVo= (UserVo)subject.getPrincipal();
        List<MenuVo> menuVoList =sysService.findUserMenu(userVo.getId());
        if(!menuVoList.isEmpty()){
            if(build!=null&&build){
                for(MenuVo menu:menuVoList){
                    for(MenuVo menu1:menuVoList){
                       if(menu1.getParentId().equals(menu.getId())){
                           menu.getSubMenuVo().add(menu1);
                       }
                    }
                }
            }
        }

        return success(menuVo);
    }
}
