package com.admin.common.util;

import com.admin.common.bean.ParamBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public class PageUtil {
    public static Page startPage(ParamBean pageParam){
        if (pageParam==null){
            return null;
        }
        Page page=null;
        if (pageParam.getPageNum()>0){
            page = PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        }
        /*
        if (!StringUtils.isEmpty(pageParamModel.orderBy())){
            PageHelper.orderBy(pageParamModel.orderBy());
        }
        */
        return page;
    }
}
