package com.linktic.order_management_service.infrastructure.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LinkTic")
                        .version("1.0.0")
                        .description("Order managament service")
                        .contact(new Contact().url("https://www.linkedin.com/in/yacson3287/")
                                .email("yacson.ramirez@gmail.com"))


                )
                .servers(Collections.singletonList(new Server().url("http://localhost:8002")));
    }
}
