package xyz.alexhaoge.zhuanglang.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import xyz.alexhaoge.zhuanglang.pojo.Teacher;
import xyz.alexhaoge.zhuanglang.service.TeacherService;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private TeacherService tService;
    

    // 简单重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    // AuthenticationException是Runtime异常，不用特别声明throw
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String userName = token.getPrincipal().toString();
        Teacher teacher = tService.getByName(userName);
        String passwordInDB = teacher.getPassword();
        String salt = teacher.getSalt();
        return new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt), getName());
    }
}
