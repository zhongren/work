package com.admin.service;

import cn.hutool.core.util.IdUtil;
import com.admin.SysConstants;
import com.admin.model.user.UserDto;
import com.admin.repo.repo.UserRepo;
import com.admin.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zhongren
 * @date 2017/11/8
 */
@Service
public class UserService extends BaseService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public void init() {
        setBaseRepo(userRepo);
    }

    /**
     * 根据账号查找
     * @param account
     * @return
     */
    public UserDto findByAccount(String account) {
        UserDto userDto =userRepo.findBy("account",account,UserDto.class);
        return userDto;
    }



    public UserDto findByOpenId(String openid) {
        UserDto userDto =userRepo.findBy("openid",openid,UserDto.class);
        return userDto;
    }
    public UserDto findById(String id) {
        UserDto userDto=findBy("id",id,UserDto.class);
        return userDto;
    }
    public UserDto create(String openid) {
        UserDto userDto =new UserDto();
        userDto.setId(IdUtil.fastUUID());
        userDto.setOpenid(openid);
        userDto.setStatus(SysConstants.USER_STATUS_ACTIVE);
        userDto.setCreateTime(new Date());
        userRepo.create(userDto);
        return userDto;
    }
    public UserDto update(UserDto userDto) {

        userRepo.update("id",userDto.getId(),userDto);
        return userDto;
    }
}
