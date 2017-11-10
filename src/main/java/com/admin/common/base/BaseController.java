package com.admin.common.base;

import com.admin.common.bean.ParamBean;
import com.admin.common.bean.ResultBean;
import com.admin.common.util.JsonUtil;
import com.admin.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);
/*
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ResultBean> baseExceptionHandler(BaseException exception) {
        logger.error(exception.getCode() + ":" + exception.getMessage(), exception);
        return null;
    }
*/

    protected String getBaseUrl() {
        String baseUrl = getRequest().getScheme() + "://" + getRequest().getServerName()
                + ":" + getRequest().getServerPort()
                + getRequest().getServletPath();
        return baseUrl;
    }

    protected HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    protected Map<String, String> getParamMap(String... except) {
        List<String> exceptList = new ArrayList<>();
        if (except != null && except.length > 0) {
            exceptList = Arrays.asList(except);
        }
        Map<String, String[]> paramMap = getRequest().getParameterMap();
        Map<String, String> result = new HashMap<>();
        if (paramMap == null || paramMap.isEmpty()) {
            return result;
        }
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            if (!exceptList.isEmpty()) {
                if (exceptList.contains(entry.getKey())) {
                    continue;
                }
            }
            result.put(entry.getKey(), StringUtil.join(entry.getValue()));
        }
        return result;
    }

    protected String getParam(String name) {
        Map<String, String> paramMap = getParamMap();
        if (paramMap == null || paramMap.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 过滤value为null和""的参数
     *
     * @param paramModel
     */
    private void filterParam(ParamBean paramModel) {
        if (paramModel == null || paramModel.isEmpty()) {
            return;
        } else {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry : paramModel.entrySet()) {
                if (entry.getValue() != null && !StringUtil.isEmpty(entry.getValue().toString())) {
                    map.put(entry.getKey(), entry.getValue());
                }
            }
            paramModel.clear();
            paramModel.putAll(map);
        }
    }

    private void soutParam(ParamBean paramModel) {
        if (paramModel == null || paramModel.isEmpty()) {
            return;
        } else {
            System.out.println("- - - - - - 请求参数 - - - - -");
            for (Map.Entry<String, Object> entry : paramModel.entrySet()) {
                System.out.println("参数:" + entry.getKey() + " 值:" + entry.getValue());
            }
            System.out.println("- - - - - - - - - -  - - - - -");
        }
    }

    /**
     * 将请求中的参数封装为ParamModel对象
     *
     * @return
     */
    protected ParamBean getParamBean() {
        ParamBean paramModel = new ParamBean();
        Integer pageNum=StringUtil.getInteger(getParam(ParamBean.PAGE_NUM));
        Integer pageSize=StringUtil.getInteger(getParam(ParamBean.PAGE_SIZE));
        paramModel.setPageNum(pageNum==null?1:pageNum);
        paramModel.setPageSize(pageSize==null?20:pageSize);
        paramModel.setOrderType(getParam(ParamBean.ORDER_TYPE));
        paramModel.setOrderField(getParam(ParamBean.ORDER_FIELD));
        paramModel.putAll(getParamMap(ParamBean.PAGE_NUM, ParamBean.PAGE_SIZE, ParamBean.ORDER_TYPE, ParamBean.ORDER_FIELD));
        soutParam(paramModel);
        filterParam(paramModel);
        return paramModel;
    }

    /**
     * 封装返回的json
     */
    protected String success(Object data, String... msg) {
        String message = "";
        ResultBean resultBean;
        if (msg.length > 0) {
            message = msg[0];
            resultBean = ResultBean.success(message, data);
        } else {
            resultBean = ResultBean.success(data);
        }
        return JsonUtil.objectToJson(resultBean);
    }
}
