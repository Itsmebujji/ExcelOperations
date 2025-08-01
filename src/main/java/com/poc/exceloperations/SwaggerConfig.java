package com.poc.exceloperations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI()
                .info(new Info()
                                .title("Excel Upload Testing API")
                                .version("1.0")
                                .description("Swagger UI for Excel Operation testing")
                        );
    }
}
