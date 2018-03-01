package com.admin.repo.repo;


import com.admin.common.base.BaseRepo;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.util.BeanUtil;
import com.admin.mapper.MapperConstant;
import com.admin.model.sys.PermPo;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_perm")
public class PermRepo extends BaseRepo {

    @Override
    public void init() {
        ConditionMap conditionMap=new ConditionMap();
        setConditionMap(conditionMap);
    }

    public  List<PermPo> findUserPermList(long userId  ){
        List<Map<String,Object>> dataList = sqlSessionTemplate.selectList(MapperConstant.findUserPerms, userId ) ;
        return BeanUtil.convertMap2List( dataList , PermPo.class  ) ;
    }
}
