package com.admin.model.sys;

import com.admin.common.base.bean.BaseVo;
import lombok.Data;

/**
 * Created by zhongr on 2017/8/28.
 */
@Data
public class RolePermRelVo extends BaseVo {

    private Long roleId;
    private Long permId;

}
