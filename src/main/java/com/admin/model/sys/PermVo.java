package com.admin.model.sys;

import com.admin.common.base.bean.BaseVo;
import lombok.Data;

/**
 * Created by zhongr on 2017/8/28.
 */
@Data
public class PermVo extends BaseVo {
    private String url;
    private String name;
    private Long menuId;
    private String menuUrl;
}
