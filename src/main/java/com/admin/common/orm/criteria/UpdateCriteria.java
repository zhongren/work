package com.admin.common.orm.criteria;

import com.admin.common.orm.condition.Condition;
import com.admin.common.orm.condition.UpdateValue;

import java.util.List;

/**
 * Created by zr on 2017/8/13.
 */
public class UpdateCriteria {
    private Object id;
    private String tableName;
    private List<Condition> conditionList;
    private List<UpdateValue> updateValueList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public UpdateCriteria(String tableName) {
        this.tableName = tableName;
    }

    public List<UpdateValue> getUpdateValueList() {
        return updateValueList;
    }

    public void setUpdateValueList(List<UpdateValue> updateValueList) {
        this.updateValueList = updateValueList;
    }
}
