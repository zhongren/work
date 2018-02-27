package com.admin.common.exception;

import com.admin.common.exception.enums.ParamEnum;

/**
 * @author zhongren
 * @date 2017/11/7
 */
public class ParamException extends BaseException {


    public ParamException(ParamEnum paramEnum) {
        super(paramEnum.getMsg(), paramEnum.getCode());
    }


}
