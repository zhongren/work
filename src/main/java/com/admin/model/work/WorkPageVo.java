package com.admin.model.work;

import com.admin.common.bean.PageInfoBean;

public class WorkPageVo extends PageInfoBean {
    private String yearTotal;
    private String monthTotal;

    public String getYearTotal() {
        return yearTotal;
    }

    public void setYearTotal(String yearTotal) {
        this.yearTotal = yearTotal;
    }

    public String getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(String monthTotal) {
        this.monthTotal = monthTotal;
    }
}
