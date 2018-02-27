package com.admin.repo.repo;

import com.admin.common.base.BaseRepo;
import com.admin.common.orm.condition.Condition;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.orm.condition.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_role")
public class SysRepo extends BaseRepo{

    @Override
    public void init() {
        ConditionMap criterionMap=new ConditionMap();
        criterionMap.put("id",new Condition("id", Op.EQ));
        criterionMap.put("name",new Condition("name", Op.LIKE));
        setConditionMap(criterionMap);
    }
}
