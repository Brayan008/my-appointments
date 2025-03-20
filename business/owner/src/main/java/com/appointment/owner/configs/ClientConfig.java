package com.appointment.owner.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
@RefreshScope
public class ClientConfig {

    @Value("${microservices.endpoint.database}")
    private String domain;

    @Bean
    public RestClient restClient() {
        log.info("Initializing RestClient with base URL: {}", domain);
        return RestClient.builder()
            .baseUrl(domain)
            .build();
    }
}
