package com.colia.yorik.yorikweb.infrastructure.security.shrio;

import com.colia.yorik.domain.model.authority.user.SysUserEntity;
import com.colia.yorik.domain.service.authority.user.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author konglingyao
 * @Date 2020/8/12
 */
public class ShiroRealm extends AuthorizingRealm implements Realm {

    @Resource
    private UserService userService;


    // 简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 获取用户
        SysUserEntity userEntity = (SysUserEntity) principalCollection.getPrimaryPrincipal();
        //TODO 通过用户 - 角色 - 权限的关联 获取  角色 权限
        //.................
        // 将角色的权限放入集合
        Set<String> rolesSet = new HashSet<>();

        // 将权限 信息放入集合
        Set<String> permsSet = new HashSet<>();
        // 权限授权
        simpleAuthorizationInfo.setStringPermissions(permsSet);
        // 角色授权
        simpleAuthorizationInfo.setRoles(rolesSet);
        return simpleAuthorizationInfo;
    }

    // shiro获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 获取用户名
        String userName = token.getPrincipal().toString();
        // 查询获取加密后的密码
        SysUserEntity user = userService.getUserByUserName(userName);

        String password = user.getPassword();
        // 盐值
        String salt = user.getSalt();
        // 进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, ByteSource.Util.bytes(salt), getName());
        // 认证通过
        return authenticationInfo;
    }

}
