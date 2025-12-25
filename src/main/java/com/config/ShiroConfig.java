package com.config;

import com.shiro.UsersRealm;
import com.shiro.YonghuRealm;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 注册yonghu自定义Realm
    @Bean
    public YonghuRealm customRealm() {
        return new YonghuRealm();
    }

    //注册管理员自定义Realm
    @Bean
    public UsersRealm UsersRealm() {
        return new UsersRealm();
    }

    // 配置SecurityManager，使用自定义Realm
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置多个Realm
        List<Realm> realms = new ArrayList<>();
        realms.add(UsersRealm()); // 管理员Realm优先
        realms.add(customRealm()); // 普通用户Realm
        securityManager.setRealms(realms);

        // 配置认证策略（默认即可，按Realm顺序认证）
        securityManager.setAuthenticator(new ModularRealmAuthenticator());
        return securityManager;
    }

    // 配置ShiroFilter（原有过滤规则保持不变）
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);


        // 配置拦截规则：LinkedHashMap保证顺序（先放行，后拦截）， 兼容原有InterceptorConfig
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // ---------- 放行：静态资源（与原有InterceptorConfig一致） ----------
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/front/**", "anon");
        filterChainDefinitionMap.put("/admin/**", "anon");
        filterChainDefinitionMap.put("/public/**", "anon");

        // ---------- 放行：Swagger相关路径（若你使用Swagger） ----------
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");

        // ---------- 放行：登录/登出相关接口 ----------
        filterChainDefinitionMap.put("/login", "anon"); // 登录接口（匿名可访问）
        filterChainDefinitionMap.put("/logout", "anon"); // 登出接口（可选放行）
        filterChainDefinitionMap.put("/user/login", "anon"); // 普通用户登录接口
        filterChainDefinitionMap.put("/admin/login", "anon"); // 管理员登录接口

        // ---------- 放行：其他匿名业务接口（根据你的项目补充） ----------
        // filterChainDefinitionMap.put("/api/public/**", "anon"); // 示例：公开API

        // ---------- 拦截：所有未放行路径需登录认证 ----------
        //filterChainDefinitionMap.put("/**", "authc");


        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilter.setLoginUrl("/login"); // 未登录时跳转的登录页
        return shiroFilter;
    }
    // ===================== 5. 开启Shiro注解支持（如@RequiresRoles） =====================
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}