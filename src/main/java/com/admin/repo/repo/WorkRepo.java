package com.admin.repo.repo;

import com.admin.common.base.BaseRepo;
import com.admin.common.orm.condition.Condition;
import com.admin.common.orm.condition.ConditionMap;
import com.admin.common.orm.condition.Op;
import com.admin.common.util.BeanUtil;
import com.admin.mapper.MapperConstant;
import com.admin.model.sys.PermPo;
import com.admin.model.work.WorkDto;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Repository
@Table(name = "tb_work")
public class WorkRepo extends BaseRepo {
    @Override
    public void init() {
        ConditionMap conditionMap = new ConditionMap();
        conditionMap.put("start", new Condition("start", Op.GE));
        conditionMap.put("end", new Condition("end", Op.LE));
        conditionMap.put("id", new Condition("id", Op.EQ));
        conditionMap.put("uid", new Condition("uid", Op.EQ));
        conditionMap.put("year", new Condition("year", Op.EQ));
        conditionMap.put("month", new Condition("month", Op.EQ));
        setConditionMap(conditionMap);
    }

    public   List<Map<String,Object>> workRankList(Map<String,Object> map){
        List<Map<String,Object>> dataList = sqlSessionTemplate.selectList(MapperConstant.workRankList,map ) ;
       // return BeanUtil.convertMap2List( dataList , WorkDto.class  ) ;
        return  dataList;
    }
}
