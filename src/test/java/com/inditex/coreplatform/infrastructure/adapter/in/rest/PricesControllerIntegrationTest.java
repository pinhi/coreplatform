package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(PricesController.class)
public class PricesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesService pricesService;

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId() throws Exception {
        String expectedStartingDate = "2020-06-15T16:01:01";

        PricesDTO mockDto = new PricesDTO();
        mockDto.setProductId(35455L);
        mockDto.setBrandId(1L);
        mockDto.setPrice(new BigDecimal("35.50"));
        mockDto.setStartDate(LocalDateTime.parse(expectedStartingDate));

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, 35455L, 1L)).thenReturn(mockDto);

        mockMvc.perform(get("/prices/")
                        .param("startDate", expectedStartingDate)
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(35455)))
                .andExpect(jsonPath("$.brandId", is(1)))
                .andExpect(jsonPath("$.price", is(35.50)))
                .andExpect(jsonPath("$.startDate", is(expectedStartingDate)));
    }

    private LocalDateTime stringDateToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        return LocalDateTime.parse(date, formatter);
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        public PricesService pricesService() {
            return Mockito.mock(PricesService.class);
        }
    }
}