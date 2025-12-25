package com.shiro;

import com.entity.YonghuEntity;
import com.service.YonghuService;
import com.utils.MyMD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class YonghuRealm extends AuthorizingRealm {

    @Autowired
    private YonghuService yonghuService; // 用户服务类，用于查询用户信息

    /**
     * 授权逻辑（根据用户角色/权限分配资源访问权限）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 这里根据实际业务实现授权逻辑（如从数据库查询用户角色/权限）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 示例：添加用户角色（实际需从数据库查询）
        //其他部分实现了角色，访问控制，此处无需操作，仅是满足shiro框架
        // info.addRole("user");
        return info;
    }

    /**
     * 认证逻辑（验证用户名和密码）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 从token中获取用户名
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String inputPassword = new String(upToken.getPassword()); // 用户输入的密码

        // 2. 查询数据库中的用户信息
        YonghuEntity user = yonghuService.selectByUsername(username); // 需实现根据用户名查询用户的方法
        if (user == null) {
            throw new UnknownAccountException("用户名不存在"); // 用户名不存在
        }

        // 3. 验证密码（使用加密工具类MD5Utils）
        boolean passwordValid = MyMD5Utils.verify(inputPassword, user.getPassword());
        if (!passwordValid) {
            throw new IncorrectCredentialsException("密码错误"); // 密码错误
        }

        // 4. 返回认证信息（包含用户信息和数据库中的加密密码，Shiro会自动处理后续逻辑）
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}