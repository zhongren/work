package com.admin.common.orm;

/**
 * @author zhongren
 * @date 2017/10/31
 */
public class Condition {
    private String column;
    private Object value;
    private String op;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
