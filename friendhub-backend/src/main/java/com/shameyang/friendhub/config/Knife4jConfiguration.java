package com.shameyang.friendhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ShameYang
 * @date 2024/5/8 16:28
 * @description 自定义 Swagger 接口文档的配置
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"dev", "test"})
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("用户中心接口文档")
                        .termsOfServiceUrl("https://github.com/shameyang")
                        .version("1.0")
                        .build())
                .select()
                //这里指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.shameyang.friendhub.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
