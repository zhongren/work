package com.admin.common.bean;

import java.util.HashMap;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class ParamBean extends HashMap<String, Object> {
    private final static String ASC = "ASC";
    private final static String DESC = "DESC";
    public final static String PAGE_SIZE = "pageSize";
    public final static String PAGE_NUM = "pageNum";
    public final static String ORDER_TYPE = "orderType";
    public final static String ORDER_FIELD = "orderField";
    private Integer pageNum;
    private Integer pageSize;
    private String orderType;
    private String orderField;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
}
