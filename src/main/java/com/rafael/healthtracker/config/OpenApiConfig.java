package com.rafael.healthtracker.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração personalizada do Swagger/OpenAPI.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Health Tracker API")
                .version("1.0")
                .description("API para monitoramento de saúde: pressão, glicemia e sono"));
    }
}
