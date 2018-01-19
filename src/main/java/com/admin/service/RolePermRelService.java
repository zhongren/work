package com.admin.service;

import com.admin.base.BaseService;
import com.admin.repo.UserRepo;
import com.admin.vo.system.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class RolePermRelService extends BaseService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void init() {
        setBaseRepo(userRepo);
    }

    public UserVo findByUserName(String userName){
       return findBy("user_name",userName,UserVo.class);
    }
}
