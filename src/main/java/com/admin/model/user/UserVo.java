package com.admin.model.user;

import com.admin.common.base.bean.BaseVo;
import com.admin.model.sys.PermPo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhongren
 * @date 2017/11/9
 */

public class UserVo extends BaseVo {
    private String name;
    private String password;
    private String account;
    private Integer status;
    private Set<String> permSet;

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

    public Set<String> getPermSet() {
        return permSet;
    }

    public void setPermSet(Set<String> permSet) {
        this.permSet = permSet;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", status=" + status +
                ", permSet=" + permSet +
                '}';
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
