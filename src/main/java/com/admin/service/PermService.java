package com.admin.service;


import com.admin.common.base.BaseService;
import com.admin.repo.repo.PermRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongr on 2017/7/31.
 */
@Service
public class PermService extends BaseService {
    @Autowired
    private PermRepo permRepo;

    @Override
    public void init() {
        setBaseRepo(permRepo);
    }

}
