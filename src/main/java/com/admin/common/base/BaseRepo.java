package com.admin.common.base;


import com.admin.common.orm.Condition;
import com.admin.common.orm.ConditionMap;

import com.admin.common.orm.criteria.SearchCriteria;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by ZR_a on 2017/8/2.
 */
public abstract class BaseRepo {

    protected static String rootMapper="com.admin.mapper.";
    private static String FIND =rootMapper+"BaseMapper.find";
    private static String CREATE=rootMapper+"BaseMapper.create";
    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    private ConditionMap conditionMap;

    public ConditionMap getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(ConditionMap conditionMap) {
        this.conditionMap = conditionMap;
    }

    @PostConstruct
    public abstract void init();

    /**
     * 多条件查询
     *
     * @param param   条件map(在具体的repo中定义)
     * @param columns
     * @return
     */
    private SearchCriteria createSearchCriteria(Map<String, Object> param, String... columns) {
        Table table = AnnotationUtils.findAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        //添加查询字段
        if (columns != null && columns.length > 0) {
            List<String> columnList = new ArrayList<>();
            columnList.addAll(Arrays.asList(columns));
            searchCriteria.setColumnList(columnList);
        }
        //添加查询条件
        if (conditionMap != null && !conditionMap.isEmpty() && param != null && !param.isEmpty()) {
            List<Condition> conditionList = new ArrayList<>();
            for (Map.Entry<String, Condition> entry : getCriterionMap().entrySet()) {
                if (!param.containsKey(entry.getKey())) {
                    continue;
                }
                Condition condition = entry.getValue();
                condition.setValue(param.get(entry.getKey()));
                conditionList.add(condition);
            }
            searchCriteria.setConditionList(conditionList);
        }
        return searchCriteria;
    }

    /**
     * 指定条件查询
     *
     * @param field   条件字段
     * @param value   条件值
     * @param columns
     * @return
     */
    private SearchCriteria createSearchCriteria(String field, Object value, String... columns) {
        Table table = AnnotationUtils.findAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        SearchCriteria searchCriteria = new SearchCriteria(tableName);
        //添加查询字段
        if (columns != null && columns.length > 0) {
            List<String> columnList = new ArrayList<>();
            columnList.addAll(Arrays.asList(columns));
            searchCriteria.setColumns(columnList);
        }
        //添加查询条件
        List<Expression> expressionList = new ArrayList<>();
        Expression expression = new Expression(field, Op.EQ, value);
        expressionList.add(expression);
        searchCriteria.setExpressions(expressionList);
        return searchCriteria;
    }

    private CreateCriteria createCreateCriteria(Map<String, Object> param){
        if (param==null||param.isEmpty()){
            throw new ParamValidateException(ParamEnum.PARAM_LACK);
        }
        Table table = AnnotationUtils.findAnnotation(this.getClass(), Table.class);
        String tableName = table.name();
        CreateCriteria createCriteria=new CreateCriteria(tableName);
        List<Expression> expressionList = new ArrayList<>();
        for (Map.Entry<String,Object> map:param.entrySet()){
            Expression expression=new Expression(map.getKey(),map.getValue());
            expressionList.add(expression);
        }
        createCriteria.setExpressions(expressionList);
        return createCriteria;
    }

    public List<Map<String, Object>> findMapList(Map<String, Object> param) {
        SearchCriteria searchCriteria = createSearchCriteria(param);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        for (Map<String, Object> map : data) {
            BeanUtil.translateMapProperty(map);
        }
        return data;
    }

    public List<Map<String, Object>> findMapListBy(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field, value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        for (Map<String, Object> map : data) {
            BeanUtil.translateMapProperty(map);
        }
        return data;
    }

    public Map<String, Object> findMapBy(String field, Object value, String... columns) {
        SearchCriteria searchCriteria = createSearchCriteria(field, value, columns);
        List<Map<String, Object>> data = sqlSessionTemplate.selectList(FIND, searchCriteria);
        if (data == null || data.isEmpty()) {
            return null;
        }
        for (Map<String, Object> map : data) {
            BeanUtil.translateMapProperty(map);
        }
        return data.get(0);
    }

    public <T> T findBy(String field, Object value, Class<T> tClass, String... columns) {
        Map<String, Object> map = findMapBy(field, value, columns);
        return BeanUtil.convertMap2Bean(map, tClass);
    }

    public <T> List<T> findListBy(String field, Object value, Class<T> tClass, String... columns) {
        List<Map<String, Object>> list = findMapListBy(field, value, columns);
        return BeanUtil.convertMap2List(list, tClass);
    }

    public int createMap(Map<String,Object> param){
        CreateCriteria createCriteria=createCreateCriteria(param);
        int succeess=sqlSessionTemplate.insert(CREATE,createCriteria);
        return succeess;
    }

    public <T> int create(T bean){
        Map<String,Object> map=BeanUtil.convertBean2Map(bean);
        return createMap(map);
    }




}
