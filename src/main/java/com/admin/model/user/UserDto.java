package com.admin.model.user;

import com.admin.common.base.bean.BaseBean;
import com.admin.common.base.bean.BaseVo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author zhongren
 * @date 2017/11/9
 */

public class UserDto extends BaseBean {
    private String id;
    private Date createTime;
    private Date updateTime;
    private String pic;
    //微信用户唯一标识
    @JsonIgnore
    private String openid;
    //微信会话密钥
    @JsonIgnore
    private String session_key;

     //姓名
    private String name;
    //状态(0禁用 1启用)
    @JsonIgnore
    private Integer status;
    //电话号码
    private String  phone;



    @JsonIgnore
    private String password;
    @JsonIgnore
    private String account;



    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
