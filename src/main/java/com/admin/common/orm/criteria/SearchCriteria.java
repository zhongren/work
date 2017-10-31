package com.admin.common.orm.criteria;


import com.admin.common.orm.Condition;

import java.util.List;

/**
 * Created by ZR_a on 2017/8/2.
 */
public class SearchCriteria {
    private String tableName;
    private List<String> columnList;
    private List<Condition> conditionList;

    public SearchCriteria(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }
}
