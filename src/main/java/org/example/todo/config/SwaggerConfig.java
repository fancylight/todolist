package org.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
//                        显示用
                        .name("JWT")
                        .build()))
                .securityContexts(Arrays.asList(SecurityContext.builder()
                        .securityReferences(Arrays.asList(SecurityReference.builder()
                                .reference("Authorization")
                                .scopes(new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
                                .build()))
                        .build()))
                .securitySchemes(Arrays.asList(new ApiKey("Authorization", "Authorization", "header")))
                // 是否开启
                .enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("org.example.todo.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("todoList")
                .description("todoList-api")
                .version("1.0.0")
                .build();
    }
}
