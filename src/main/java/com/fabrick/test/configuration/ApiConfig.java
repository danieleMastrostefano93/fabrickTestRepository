package com.fabrick.test.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for rest APIs
 */
@Configuration
@Data
public class ApiConfig {

    @Value("${auth-schema}")
    private String authSchema;

    @Value("${api-key}")
    private String apiKey;

    @Value("${balance.url}")
    private String balanceUrl;

    @Value("${payment.url}")
    private String paymentUrl;

    @Value("${transactions.url}")
    private String transactionsUrl;

}
