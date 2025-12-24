package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import com.handler.IdCardTypeHandler;
import org.mybatis.spring.annotation.MapperScan;



import com.handler.PasswdTypeHandler;
import com.utils.SpringContextUtils;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;


/**
 * mybatis-plus配置
 */
@Configuration
//@MapperScan("com.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }

    // 注册身份证号类型处理器
    @Bean
    public IdCardTypeHandler idCardTypeHandler() {
        return new IdCardTypeHandler();
    }

    //注册密码类型处理器，不可注册，应handle/PasswdTypeHandler中使用了@Component:生成与类名同名的组件
//    @Bean
//    public PasswdTypeHandler passwdTypeHandler() {
//        return new PasswdTypeHandler();
//    }

}