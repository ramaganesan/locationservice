package com.udacity.locationservice.config;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Not Authorized"),
        @ApiResponse(code = 500, message =  "Fatal Error")
})
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                   .select()
                   .apis(RequestHandlerSelectors.any())
                   .paths(PathSelectors.any())
                   .build()
                   .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                           "Location API",
                "This is a Location API",
                "1.0",
                "Open Source",
                new Contact("Rama ganesan","github.com", "rama.ganesan2@gmail.com"),
                "Open Source",
                "N/A",
                Collections.emptyList());
    }
}
