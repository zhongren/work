package com.admin.common.bean;

import com.admin.common.base.bean.BaseBean;

import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class PageInfoBean extends BaseBean {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private Integer pages;
    private List<Map<String, Object>> data;

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

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
