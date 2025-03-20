package com.appointment.database.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
   @Value("${openapi.service.url-database}")
   private String urlDatabase;

   @Bean
   public OpenAPI userOpenAPI(
      @Value("${openapi.service.title}") String serviceTitle,
      @Value("${openapi.service.version}") String serviceVersion) {
      return new OpenAPI()
         .servers(List.of(new Server().url(urlDatabase)))
         .info(new Info().title(serviceTitle).version(serviceVersion));
   }
}
