package com.admin.service;


import com.admin.model.perm.PermVo;
import com.admin.repo.repo.PermRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by zhongr on 2017/8/28.
 */
@Service
public class SysService {
    @Autowired
    private PermRepo permRepo;

    public List<PermVo> findUserPermList(long userId) {
        List<PermVo> permVoList = permRepo.findUserPermList(userId);
        return permVoList;
    }
}
