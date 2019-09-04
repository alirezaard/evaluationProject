package com.gamelectronics.evaluateProject.swaggerconfig;


import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket gamelectronicsApi() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.gamelectronics.evaluateProject.controller.impl"))
                    .paths(PathSelectors.regex("/.*"))
                    .build().apiInfo(apiInfo());
        }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Evaluation Project REST API Documentation")
                .contact("gamelectronics Co. (@gamelectronics)")
                .version("1.0")
                .build();
    }
}
