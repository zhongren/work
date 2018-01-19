package com.admin.common.shiro;


import com.admin.common.util.ApplicationContextUtil;
import com.admin.common.util.StringUtil;
import com.admin.model.perm.PermVo;
import com.admin.model.sys.MenuPermVo;
import com.admin.model.sysenum.UserStatusEnum;
import com.admin.model.user.UserVo;
import com.admin.service.SysService;
import com.admin.service.UserService;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZR_a on 2017/8/24.
 */
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private SysService sysService;

    // 菜单权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserVo userVo = (UserVo) principalCollection.getPrimaryPrincipal();
        Set<String> perms = findUserPerms(userVo.getId());
        SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
        authorization.setStringPermissions(perms);
        return authorization;
    }

    // 登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String account = token.getUsername();
        UserVo userVo = ApplicationContextUtil.getBean(UserService.class).findByAccount(account);
        if (userVo == null) {
            //用户不存在
            throw new UnknownAccountException();
        }
        if (userVo.getStatus() == UserStatusEnum.DISABLE.getCode()) {
            //用户被禁用
            throw new DisabledAccountException();
        }

        return new SimpleAuthenticationInfo(userVo, userVo.getPassword(), getName());
    }

    /**
     * 设置自定义密码匹配验证器（在application-shiro.xml中注入）
     *
     * @param myCredentialsMatcher
     */
    public void setCredentialsMatcher(CredentialsMatcher myCredentialsMatcher) {
        super.setCredentialsMatcher(myCredentialsMatcher);
    }

    private Set<String> findUserPerms(Long userId) {
        if (userId == null) {
            return null;
        }
        SysService sysService = ApplicationContextUtil.getBean(SysService.class);
        List<PermVo> permVoList = sysService.findUserPermList(userId);
        Set<String> userPerms = new HashSet<>();

        for (PermVo permVo : permVoList) {
            String permUrl = permVo.getUrl();
            String menuUrl = permVo.getMenuUrl();
            String perm = menuUrl + ":" + permUrl;
            userPerms.add(perm);
        }
        return userPerms;
    }


}
