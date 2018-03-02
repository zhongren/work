package com.admin.service;


import com.admin.model.sys.MenuVo;
import com.admin.model.sys.PermPo;
import com.admin.repo.repo.MenuRepo;
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
    @Autowired
    private MenuRepo menuRepo;
    /**
     * 获取用户权限集合
     * @param userId
     * @return
     */
    public List<PermPo> findUserPerm(long userId) {
        List<PermPo> permPoList = permRepo.findUserPerm(userId);
        return permPoList;
    }

    /**
     * 获取用户菜单集合
     * @param userId
     * @return
     */
    public List<MenuVo> findUserMenu(long userId) {
        List<MenuVo> menuVoList = menuRepo.findUserMenu(userId);
        return menuVoList;
    }
}
