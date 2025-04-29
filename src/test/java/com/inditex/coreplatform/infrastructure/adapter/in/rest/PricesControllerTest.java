package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.application.service.impl.PricesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PricesControllerTest {

    @InjectMocks
    private PricesController pricesController;

    @Mock
    private PricesServiceImpl pricesService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnPricesDTOWhenValidInput_aka_prueba1() {
        // GIVEN
        String startDate = "2020-06-15T16:00:00";
        Long productId = 4L;
        Long brandId = 1L;

        PricesDTO expectedPricesDTO = new PricesDTO();
        expectedPricesDTO.setBrandId(brandId);
        expectedPricesDTO.setProductId(productId);
        expectedPricesDTO.setStartDate(LocalDateTime.parse(startDate));

        when(pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId))
                .thenReturn(Optional.of(expectedPricesDTO));

        // WHEN
        ResponseEntity<PricesDTO> foundPrices = pricesController.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);

        // THEN
        assertNotNull(foundPrices);
        assertNotNull(foundPrices.getBody());
        assertAll(
                () -> assertEquals(brandId, foundPrices.getBody().getBrandId(), "Brand ID should match"),
                () -> assertEquals(productId, foundPrices.getBody().getProductId(), "Product ID should match"),
                () -> assertEquals(expectedPricesDTO.getStartDate(), foundPrices.getBody().getStartDate(), "Start date should match")
        );
    }

    @Test
    public void shouldThrowExceptionWhenServiceFails() {
        // GIVEN
        String startDate = "2020-06-15T16:00:00";
        Long productId = 4L;
        Long brandId = 1L;

        when(pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId))
                .thenThrow(new RuntimeException("Service error"));

        // WHEN
        Executable executable = () -> pricesController.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);

        // THEN
        RuntimeException exception = assertThrows(RuntimeException.class, executable);
        assertEquals("Service error", exception.getMessage());
    }

}