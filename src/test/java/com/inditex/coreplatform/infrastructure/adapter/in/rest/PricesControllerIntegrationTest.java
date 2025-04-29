package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.TestContextConfiguration;
import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricesController.class)
@Import(TestContextConfiguration.class)
public class PricesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesService pricesService;

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_aka_prueba1() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-14T10:00:00";
        String expectedEndDate = "2077-01-01T01:01:01";
        Long expectedProductId = 35455L;
        Long expectedBrandId = 1L;
        BigDecimal expectedPrice = new BigDecimal("35.50");

        PricesDTO mockDto = getPricesDTOMock(expectedStartingDate, expectedEndDate);

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, expectedProductId, expectedBrandId)).thenReturn(Optional.of(mockDto));

        // WHEN - THEN
        performRequestAndValidateIt(expectedStartingDate, expectedProductId, expectedBrandId, expectedPrice, expectedEndDate);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_aka_prueba2() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-14T16:00:00";
        String expectedEndDate = "2077-01-01T01:01:01";
        Long expectedProductId = 35455L;
        Long expectedBrandId = 1L;
        BigDecimal expectedPrice = new BigDecimal("35.50");

        PricesDTO mockDto = getPricesDTOMock(expectedStartingDate, expectedEndDate);

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, expectedProductId, expectedBrandId)).thenReturn(Optional.of(mockDto));

        // WHEN - THEN
        performRequestAndValidateIt(expectedStartingDate, expectedProductId, expectedBrandId, expectedPrice, expectedEndDate);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_aka_prueba3() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-14T21:00:00";
        String expectedEndDate = "2077-01-01T01:01:01";
        Long expectedProductId = 35455L;
        Long expectedBrandId = 1L;
        BigDecimal expectedPrice = new BigDecimal("35.50");

        PricesDTO mockDto = getPricesDTOMock(expectedStartingDate, expectedEndDate);

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, expectedProductId, expectedBrandId)).thenReturn(Optional.of(mockDto));

        // WHEN - THEN
        performRequestAndValidateIt(expectedStartingDate, expectedProductId, expectedBrandId, expectedPrice, expectedEndDate);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_aka_prueba4() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-15T10:00:00";
        String expectedEndDate = "2077-01-01T01:01:01";
        Long expectedProductId = 35455L;
        Long expectedBrandId = 1L;
        BigDecimal expectedPrice = new BigDecimal("35.50");

        PricesDTO mockDto = getPricesDTOMock(expectedStartingDate, expectedEndDate);

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, expectedProductId, expectedBrandId)).thenReturn(Optional.of(mockDto));

        // WHEN - THEN
        performRequestAndValidateIt(expectedStartingDate, expectedProductId, expectedBrandId, expectedPrice, expectedEndDate);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_aka_prueba5() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-16T21:00:00";
        String expectedEndDate = "2077-01-01T01:01:01";
        Long expectedProductId = 35455L;
        Long expectedBrandId = 1L;
        BigDecimal expectedPrice = new BigDecimal("35.50");

        PricesDTO mockDto = getPricesDTOMock(expectedStartingDate, expectedEndDate);

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, expectedProductId, expectedBrandId)).thenReturn(Optional.of(mockDto));

        // WHEN - THEN
        performRequestAndValidateIt(expectedStartingDate, expectedProductId, expectedBrandId, expectedPrice, expectedEndDate);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_NotFound() throws Exception {
        // GIVEN
        Long productId = 35455L;
        Long brandId = 1L;
        String expectedStartingDate = "2020-06-15T16:01:01";
        String expectedProductId = "451";
        String expectedBrandId = "626";

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                expectedStartingDate, productId, brandId)).thenReturn(Optional.empty());

        // WHEN - THEN
        mockMvc.perform(get("/prices/")
                        .param("startDate", expectedStartingDate)
                        .param("productId", expectedProductId)
                        .param("brandId", expectedBrandId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_InternalServerError() throws Exception {
        // GIVEN
        String expectedStartingDate = "2020-06-15T16:01:01";
        Long productId = 5555L;
        Long brandId = 42L;

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(
                        expectedStartingDate, productId, brandId))
                .thenThrow(new RuntimeException("Unexpected error"));

        // WHEN - THEN
        mockMvc.perform(get("/prices/")
                        .param("startDate", expectedStartingDate)
                        .param("productId", productId.toString())
                        .param("brandId", brandId.toString()))
                .andExpect(status().isInternalServerError());
    }

    private void performRequestAndValidateIt(String expectedStartingDate, Long expectedProductId, Long expectedBrandId, BigDecimal expectedPrice, String expectedEndDate) throws Exception {
        mockMvc.perform(get("/prices/")
                        .param("startDate", expectedStartingDate)
                        .param("productId", expectedProductId.toString())
                        .param("brandId", expectedBrandId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(expectedProductId.intValue())))
                .andExpect(jsonPath("$.brandId", is(expectedBrandId.intValue())))
                .andExpect(jsonPath("$.price", is(expectedPrice.doubleValue())))
                .andExpect(jsonPath("$.endDate", is(expectedEndDate)))
                .andExpect(jsonPath("$.startDate", is(expectedStartingDate)));
    }

    private static PricesDTO getPricesDTOMock(String expectedStartingDate, String expectedEndDate) {
        PricesDTO mockDto = new PricesDTO();
        mockDto.setProductId(35455L);
        mockDto.setBrandId(1L);
        mockDto.setPrice(new BigDecimal("35.50"));
        mockDto.setStartDate(LocalDateTime.parse(expectedStartingDate));
        mockDto.setEndDate(LocalDateTime.parse(expectedEndDate));
        return mockDto;
    }

}