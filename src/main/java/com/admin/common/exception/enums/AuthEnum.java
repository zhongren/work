package com.admin.common.exception.enums;


import com.admin.common.bean.ResultBean;

/**
 * Created by zhongr on 2017/8/25.
 */
public enum AuthEnum {
    UNKNOWN_ACCOUNT(ResultBean.NO_AUTH,"用户不存在"),DISABLED_ACCOUNT(ResultBean.NO_AUTH,"用户被禁用,请联系管理员") ,
    WRONG_PASSWORD(ResultBean.NO_AUTH,"用户名或密码错误"),UNAUTHORIZED(ResultBean.NO_AUTH,"权限不足,请重新登陆") ,UNLOGIN(ResultBean.NO_AUTH,"未登录,请先登陆")  ;
    private String code;
   private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    AuthEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
