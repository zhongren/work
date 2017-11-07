package com.admin.common.exception;

import com.admin.common.exception.enums.ParamEnum;

/**
 * @author zhongren
 * @date 2017/11/7
 */
public class ParamValidateException extends BaseException {


    public ParamValidateException(ParamEnum paramEnum) {
        super(paramEnum.getMsg(), paramEnum.getCode());
    }


}
