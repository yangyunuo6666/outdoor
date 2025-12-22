package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.any())
                .build();
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("露营设备租赁系统API 文档")
                .description("项目 API 接口文档")
                .version("1.0.0")
                .contact(new Contact(
                        "yagnyunuo",
                        "https://github.com/yangyunuo6666/outdoor",
                        "yangyunuo6666@gamil.com"))
                .build();
    }

    // JWT 认证支持（可选）
//    private List<SecurityScheme> securitySchemes() {
//        return Collections.singletonList(
//                new ApiKey("Authorization", "Authorization", "header")
//        );
//    }

//    private List<SecurityContext> securityContexts() {
//        return Collections.singletonList(
//                SecurityContext.builder()
//                        .securityReferences(defaultAuth())
//                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
//                        .build()
//        );
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope =
//                new AuthorizationScope("global", "accessEverything");
//        return Collections.singletonList(
//                new SecurityReference("Authorization",
//                        new AuthorizationScope[]{authorizationScope})
//        );
//    }
}
