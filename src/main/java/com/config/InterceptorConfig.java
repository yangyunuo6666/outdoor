package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
	
	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthorizationInterceptor())
				//.addPathPatterns("/**")
				.excludePathPatterns(
						// ===== Swagger 2.x UI 核心路径 =====
						"/swagger-ui.html",          // UI主页面
						"/swagger-resources/**",     // 配置元数据（含子路径）
						"/swagger-resources/configuration/ui", // UI配置请求
						"/swagger-resources/configuration/security", // 安全配置请求
						"/v2/api-docs/**",           // API文档接口（已生效，保留）
						"/v2/api-docs-ext/**",
						// ===== UI静态资源路径 =====
						"/webjars/springfox-swagger-ui/**", // Swagger UI依赖的webjars资源
						"/webjars/**",
						"/static/**");
        super.addInterceptors(registry);
	}
	
	/**
	 * springboot 2.0配置WebMvcConfigurationSupport之后，会导致默认配置被覆盖，要访问静态资源需要重写addResourceHandlers方法
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// ===== 新增：Swagger 2.x 核心静态资源映射 =====
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");

		registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/admin/")
        .addResourceLocations("classpath:/img/")
        .addResourceLocations("classpath:/front/")
        .addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
    }
}
