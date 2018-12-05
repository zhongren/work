package com.admin.repo.repo;

import com.admin.common.base.BaseRepo;
import com.admin.common.orm.condition.Condition;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.orm.condition.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Repository
@Table(name = "tb_user")
public class UserRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap = new ConditionMap();
        conditionMap.put("id", new Condition("id", Op.EQ));
        conditionMap.put("account", new Condition("account", Op.LIKE));
        conditionMap.put("name", new Condition("name", Op.LIKE));
        conditionMap.put("status", new Condition("status", Op.EQ));
        setConditionMap(conditionMap);
    }
}
