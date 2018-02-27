package com.admin.common.base;

import com.admin.common.bean.PageInfoBean;
import com.admin.common.bean.ParamBean;
import com.admin.common.util.PageUtil;
import com.github.pagehelper.Page;

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

    /**
     * 分页查询
     *
     * @param paramBean
     * @return
     */
    public PageInfoBean page(ParamBean paramBean) {
        Page page = PageUtil.startPage(paramBean);
        List<Map<String, Object>> data = baseRepo.findMapList(paramBean);
        return createPageInfo(page, data);
    }

    /**
     * 根据条件查询对象
     *
     * @param field
     * @param value
     * @param tClass
     * @param columns
     * @param <T>
     * @return
     */
    public <T> T findBy(String field, String value, Class<T> tClass, String... columns) {
        return baseRepo.findBy(field, value, tClass, columns);

    }

    /**
     * 创建对象
     *
     * @param bean
     * @param <T>
     * @return
     */
    public <T> Long create(T bean) {
        return (Long) baseRepo.create(bean);
    }

    /**
     * 更新对象
     *
     * @param by
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public <T> int update(String by, Object value, T bean) {
        return baseRepo.update(by, value, bean);
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
