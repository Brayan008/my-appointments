package com.appointment.gatewayserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(GatewayServerApplication.class, args);
   }

   @Bean
   public RouteLocator routeLocator(RouteLocatorBuilder builder) {
      return builder
         .routes()
         .route(r -> r.path("/api/auth/v3/api-docs").and().method(HttpMethod.GET).uri("lb://auth"))
         .build();
   }
}
