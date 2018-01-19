package com.admin.common.base.bean;

import java.io.Serializable;

/**
 * @author zhongren
 * @date 2017/11/10
 */
public  class BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
