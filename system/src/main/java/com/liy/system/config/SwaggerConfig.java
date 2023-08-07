package com.liy.system.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiY
 * Swagger配置类
 */

//@Configuration
@OpenAPIDefinition(
        info = @Info(
                //参数的内容都是根据自己的项目自己起的
                title = "emos-api",
                description = "Emos管理系统的后端Java项目",
                version = "1.0"
        )
)
//为Swagger整合JWT支持，项目没有令牌模块下面这个注解可以不写
@SecurityScheme(
        name = "Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)

public class SwaggerConfig {

//
//    @Bean
//    public OpenAPI springShopOpenAPI() {
//            return new OpenAPI()
//                    .info(new Info().title("十六进制说")
//                            .description("十六进制说API文档")
//                            .version("v1")
//                            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
//                    .externalDocs(new ExternalDocumentation()
//                            .description("外部文档")
//                            .url("https://springshop.wiki.github.org/docs"));
//    }

}
