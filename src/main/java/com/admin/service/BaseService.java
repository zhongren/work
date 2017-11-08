package com.admin.service;

import com.admin.common.base.PageInfoBean;
import com.admin.common.base.ParamBean;
import com.admin.common.orm.BaseRepo;
import com.admin.common.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */
public abstract class BaseService {
    private BaseRepo baseRepo;

    public BaseRepo getBaseRepo() {
        return baseRepo;
    }

    public void setBaseRepo(BaseRepo baseRepo) {
        this.baseRepo = baseRepo;
    }

    @PostConstruct
    public abstract void init();

    public PageInfoBean findPage(ParamBean paramBean) {
        Page page = PageUtil.startPage(paramBean);
        List<Map<String, Object>> data = baseRepo.findMapList(paramBean);
        return createPageInfo(page, data);
    }

    private PageInfoBean createPageInfo(Page page, List<Map<String, Object>> data) {
        PageInfoBean pageInfoBean = new PageInfoBean();
        pageInfoBean.setData(data);
        pageInfoBean.setPageNum(page.getPageNum());
        pageInfoBean.setPageSize(page.getPageSize());
        pageInfoBean.setTotal(page.getTotal());
        pageInfoBean.setPages(page.getPages());
        return pageInfoBean;
    }

}
