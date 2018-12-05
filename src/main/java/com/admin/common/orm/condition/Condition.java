package com.admin.common.orm.condition;

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

    public Condition(String column, String op,Object value ) {
        this.column = column;
        this.value = value;
        this.op = op;
    }

    public Condition(String column, String op) {
        this.column = column;
        this.op = op;
    }

    public Condition(String column, Object value) {
        this.column = column;
        this.value = value;
    }
}
