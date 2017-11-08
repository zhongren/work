package com.admin.repo.repo;

import com.admin.common.orm.BaseRepo;
import com.admin.common.orm.condition.Condition;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.orm.condition.Op;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
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
        setConditionMap(conditionMap);
    }
}
