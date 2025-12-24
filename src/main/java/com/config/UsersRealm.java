package com.config;


import com.entity.UsersEntity;
import com.service.UsersService;
import com.utils.MyMD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersRealm extends AuthenticatingRealm {

    @Autowired
    private UsersService UsersService;// 管理员服务类，用于查询管理员信息

//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        // 返回空的授权信息，满足父类方法要求，不执行实际授权逻辑
//        return new SimpleAuthorizationInfo();
//    }
    /**
     * 授权逻辑（根据用户角色/权限分配资源访问权限）
     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        // 这里根据实际业务实现授权逻辑（如从数据库查询用户角色/权限）
////        SimpleAuthorizationInfo info = SimpleAuthorizationInfo();
//        // 示例：添加用户角色（实际需从数据库查询）
//        //其他部分实现了角色，访问控制，此处无需操作，仅是满足shiro框架
//        // info.addRole("user");
//        return new SimpleAuthorizationInfo();
//    }

    /**
     * 认证逻辑（验证用户名和密码）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String inputPassword = new String(upToken.getPassword());

        // 查询管理员信息
        UsersEntity admin = UsersService.selectByUsersUsername(username);
        if (admin == null) {
            throw new UnknownAccountException("管理员用户名不存在");
        }

        // 验证管理员密码
        boolean passwordValid = MyMD5Utils.verify(inputPassword, admin.getPassword());
        if (!passwordValid) {
            throw new IncorrectCredentialsException("管理员密码错误");
        }

        return new SimpleAuthenticationInfo(admin, admin.getPassword(), getName());
    }
}