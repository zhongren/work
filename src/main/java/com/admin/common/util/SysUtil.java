package com.admin.common.util;

import com.admin.model.user.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

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

    public static UserVo getPrincipal(){
        Subject subject = SecurityUtils.getSubject() ;
        Object principal = subject.getPrincipal() ;
        return principal == null ? null : (UserVo) principal ;
    }
}
