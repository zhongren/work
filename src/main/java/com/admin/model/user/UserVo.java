package com.admin.model.user;

import com.admin.common.base.bean.BaseBean;
import com.admin.common.base.bean.BaseVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongren
 * @date 2017/11/9
 */
@Data
public class UserVo extends BaseVo {
    private String name;
    private String password;
    private String account;
    private int status;


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
