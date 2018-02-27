package com.admin.common.exception;


import com.admin.common.exception.enums.AuthEnum;

/**
 * Created by zhongr on 2017/8/25.
 */
public class AuthException extends BaseException {

    public AuthException(AuthEnum authEnum) {
        super(authEnum.getMsg(),authEnum.getCode());
    }
}
