package com.admin.model.sysenum;

/**
 * Created by ZR_a on 2018/1/19.
 */
public enum UserStatusEnum {
    ENABLE("启用", 1), DISABLE("禁用", 0);

    private String name;
    private int code;

    // 构造方法
    private UserStatusEnum(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
