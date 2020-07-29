package com.qiqi.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.qiqi.entity.User;
import com.qiqi.service.UserService;
import com.qiqi.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Realm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;

}
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken=(JwtToken) authenticationToken;
        String uid = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Long.valueOf(uid));
        if (user == null){
            throw new UnknownAccountException("用户不存在");
        }
        if (user.getStatus()==-1){
            throw  new LockedAccountException("账户已被锁定");
        }
        RealmProfile realmProfile=new RealmProfile();
        BeanUtil.copyProperties(user,realmProfile);
        return new SimpleAuthenticationInfo(realmProfile,jwtToken.getCredentials(),getName());


    }
}
