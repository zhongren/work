package com.admin.model.user;

import com.admin.common.base.BaseBean;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongren
 * @date 2017/11/9
 */
public class UserPo extends BaseBean {
    private String id;
    private String userName;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UserVo po2Vo(UserPo po) {
        if (po == null) {
            return null;
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public List<UserVo> po2Vo(List<UserPo> poList) {
        if (poList == null || poList.isEmpty()) {
            return null;
        }
        List<UserVo> voList = new ArrayList<>();
        for (UserPo po : poList) {
            UserVo vo = po2Vo(po);
            voList.add(vo);
        }
        return voList;
    }
}
