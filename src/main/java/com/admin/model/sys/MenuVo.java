package com.admin.model.sys;

import com.admin.common.base.bean.BaseVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongr on 2017/8/28.
 */
@Data
public class MenuVo extends BaseVo {
    private String url;
    private String name;
    private Long parentId;
    private Integer level;
    private String icon;
    private List<MenuVo> subMenuVo=new ArrayList<>();
}
