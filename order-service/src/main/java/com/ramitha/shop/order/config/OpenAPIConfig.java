package com.ramitha.shop.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI OrderServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Order Service API")
                        .description("This is the order service API.")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Order Service Documentation")
                        .url("https://example.com/docs/order-service"));
    }
}
