package com.admin.common.base;


import com.admin.common.bean.ResultBean;
import com.admin.common.exception.AuthException;

import com.admin.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Created by zhongr on 2017/8/25.
 */
@ControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     * 登录异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ResultBean> authExceptionHandler(AuthException exception) {
        logger.error(exception.getCode() + ":" + exception.getMessage(), exception);
        return response(getExceptionResultBean(exception), HttpStatus.OK);
    }

    /**
     * 普通业务异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResultBean> bussinessExceptionHandler(BaseException exception) {
        logger.error(exception.getCode() + ":" + exception.getMessage(), exception);
        return response(getExceptionResultBean(exception), HttpStatus.OK);
    }

    private ResultBean getExceptionResultBean(BaseException exception) {
        return new ResultBean(exception.getCode(), exception.getMessage());
    }

    private ResponseEntity<ResultBean> response(ResultBean resultBean, HttpStatus status) {
        return resopnse(resultBean.getCode(), resultBean.getMsg(), resultBean.getData(), status);
    }

    private ResponseEntity<ResultBean> resopnse(String code, String msg, Object data, HttpStatus status) {
        ResultBean resultBean = ResultBean.build(code, msg, data);
        return new ResponseEntity<>(resultBean, status);
    }

}
