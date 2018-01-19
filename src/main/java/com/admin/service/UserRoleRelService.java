package com.admin.service;

import com.admin.base.BaseService;
import com.admin.repo.UserRoleRelRepo;
import com.admin.vo.system.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class UserRoleRelService extends BaseService {
    @Autowired
    private UserRoleRelRepo userRoleRelRepo;

    @Override
    public void init() {
        setBaseRepo(userRoleRelRepo);
    }

    public RoleVo findByUserId(String userId){
       return findBy("user_id",userId,RoleVo.class);
    }
}
