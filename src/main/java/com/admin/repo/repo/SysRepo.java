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
@Table(name = "tb_role")
public class SysRepo extends BaseRepo{

    @Override
    public void init() {
        CriterionMap criterionMap=new CriterionMap();
        criterionMap.put("id",new Expression("id", Op.EQ));
        criterionMap.put("name",new Expression("name", Op.LIKE));
        setCriterionMap(criterionMap);
    }
}
