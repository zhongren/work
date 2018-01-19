package com.admin.repo.repo;

import com.admin.base.BaseRepo;
import com.admin.base.CriterionMap;
import com.admin.base.Expression;
import com.admin.base.Op;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by ZR_a on 2017/8/1.
 */
@Repository
@Table(name = "tb_role_perm")
public class RolePermRelRepo extends BaseRepo{

    @Override
    public void init() {
        CriterionMap criterionMap=new CriterionMap();
        criterionMap.put("id",new Expression("id", Op.EQ));
        criterionMap.put("roleId",new Expression("role_id", Op.EQ));
        criterionMap.put("permId",new Expression("perm_id", Op.EQ));
        setCriterionMap(criterionMap);
    }
}
