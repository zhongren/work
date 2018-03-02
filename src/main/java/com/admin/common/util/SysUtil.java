package com.admin.common.util;

import com.admin.model.user.UserVo;
import org.apache.shiro.SecurityUtils;

public class SysUtil {
    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static UserVo getLoginUser() {
        UserVo user = (UserVo) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
