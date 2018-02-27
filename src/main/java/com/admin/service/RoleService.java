package com.admin.service;


import com.admin.common.base.BaseService;
import com.admin.model.sys.RoleVo;
import com.admin.repo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class RoleService extends BaseService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void init() {
        setBaseRepo(roleRepo);
    }

    public RoleVo findByName(String name){
       return findBy("name",name,RoleVo.class);
    }
}
