package xyz.alexhaoge.zhuanglang.config;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("xyz.alexhaoge.zhuanglang"))
                .paths(PathSelectors.any())
                .build();
    }
        
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("庄浪教学资源平台接口文档")
            .description("教学资源平台后端的开放接口")
            // .termsOfServiceUrl("http://www.1314sl.com")
            .version("0.1.0")
            .build();
    }
}