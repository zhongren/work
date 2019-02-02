package com.admin.model.work;

import com.admin.common.bean.PageInfoBean;

public class AllWorkPageVo extends PageInfoBean {
    private String yearTotal;
    private String yearAverage;
    private String myTotal;

    public String getYearTotal() {
        return yearTotal;
    }

    public void setYearTotal(String yearTotal) {
        this.yearTotal = yearTotal;
    }

    public String getYearAverage() {
        return yearAverage;
    }

    public void setYearAverage(String yearAverage) {
        this.yearAverage = yearAverage;
    }

    public String getMyTotal() {
        return myTotal;
    }

    public void setMyTotal(String myTotal) {
        this.myTotal = myTotal;
    }
}
