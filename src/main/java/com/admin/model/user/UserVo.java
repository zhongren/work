package com.admin.model.user;

import com.admin.common.base.BaseBean;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongren
 * @date 2017/11/9
 */
public class UserVo extends BaseBean {
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

    public UserPo vo2Po(UserVo vo) {
        if (vo == null) {
            return null;
        }
        UserPo po = new UserPo();
        BeanUtils.copyProperties(vo, po);
        return po;
    }

    public List<UserPo> vo2Po(List<UserVo> voList) {
        if (voList == null || voList.isEmpty()) {
            return null;
        }
        List<UserPo> poList = new ArrayList<>();
        for (UserVo vo : voList) {
            UserPo po = vo2Po(vo);
            poList.add(po);
        }
        return poList;
    }
}
