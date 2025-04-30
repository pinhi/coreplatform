package com.inditex.coreplatform.application;

import com.inditex.coreplatform.application.service.PricesService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestContextConfiguration {

    @Bean
    public PricesService pricesService() {
        return Mockito.mock(PricesService.class);
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        return CircuitBreakerRegistry.ofDefaults();
    }
}