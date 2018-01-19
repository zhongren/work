package com.admin.model.user;

import com.admin.common.base.bean.BaseBean;
import com.admin.common.base.bean.BasePo;
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
public class UserPo extends BasePo {
    private String name;
    private String password;
    private String account;
    private int status;
    public static UserVo po2Vo(UserPo po) {
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
