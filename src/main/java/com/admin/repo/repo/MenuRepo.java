package com.admin.repo.repo;


import com.admin.common.base.BaseRepo;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.util.BeanUtil;
import com.admin.mapper.MapperConstant;
import com.admin.model.sys.MenuVo;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_menu")
public class MenuRepo extends BaseRepo {

    @Override
    public void init() {
        ConditionMap conditionMap=new ConditionMap();
        setConditionMap(conditionMap);
    }

    public  List<MenuVo> findUserMenu(long userId  ){
        List<Map<String,Object>> dataList = sqlSessionTemplate.selectList(MapperConstant.findUserMenu, userId ) ;
        return BeanUtil.convertMap2List( dataList , MenuVo.class  ) ;
    }
}
