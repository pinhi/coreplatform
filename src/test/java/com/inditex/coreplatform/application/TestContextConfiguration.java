package com.inditex.coreplatform.application;

import com.inditex.coreplatform.application.service.PricesService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestContextConfiguration {

    @Bean
    public PricesService pricesService() {
        return Mockito.mock(PricesService.class);
    }
}