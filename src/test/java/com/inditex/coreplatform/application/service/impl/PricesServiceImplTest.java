package com.inditex.coreplatform.application.service.impl;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.domain.port.PricesRepository;
import com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper.PricesDomainDTOMapper;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import com.inditex.coreplatform.infrastructure.adapter.out.mapper.PricesDomainEntityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PricesServiceImplTest {

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PricesDomainDTOMapper pricesDomainDTOMapper;

    @Mock
    private PricesDomainEntityMapper pricesDomainEntityMapper;

    @InjectMocks
    private PricesServiceImpl pricesService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId() {
        // given
        String startDate = "2020-06-15T16:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        PricesEntity expectedEntity = getPricesEntityMock(brandId, productId, startDate);
        Prices expectedDomain = getPricesDomainMock(brandId, productId, startDate);
        PricesDTO expectedDto = getPricesDTOMock(brandId, productId, startDate);

        when(pricesRepository.findPricesByStartDateAndProductIdAndBrandId(LocalDateTime.parse(startDate), productId, brandId))
                .thenReturn(Optional.ofNullable(expectedEntity));
        when(pricesDomainEntityMapper.toDomain(expectedEntity)).thenReturn(expectedDomain);
        when(pricesDomainDTOMapper.toDTO(expectedDomain)).thenReturn(expectedDto);

        // when
        Optional<PricesDTO> resultOpt = pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);
        PricesDTO result = resultOpt.orElse(null);

        // then
        assertNotNull(result);
        assertEquals(expectedDto, result);
        verify(pricesRepository, times(1)).findPricesByStartDateAndProductIdAndBrandId(LocalDateTime.parse(startDate), productId, brandId);
        assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), result.getStartDate());
        assertEquals(productId, result.getProductId());
        assertEquals(brandId, result.getBrandId());
    }

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId_cache() {
        // given
        String startDate = "2020-06-15T16:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        PricesEntity expectedEntity = getPricesEntityMock(brandId, productId, startDate);
        Prices expectedDomain = getPricesDomainMock(brandId, productId, startDate);
        PricesDTO expectedDto = getPricesDTOMock(brandId, productId, startDate);

        when(pricesRepository.findPricesByStartDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(Optional.ofNullable(expectedEntity));
        when(pricesDomainEntityMapper.toDomain(expectedEntity)).thenReturn(expectedDomain);
        when(pricesDomainDTOMapper.toDTO(expectedDomain)).thenReturn(expectedDto);

        // when
        Optional<PricesDTO> firstInvocation = pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);
        Optional<PricesDTO> secondInvocation = pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);

        // then
        Assertions.assertTrue(firstInvocation.isPresent());
        Assertions.assertTrue(secondInvocation.isPresent());
        Assertions.assertSame(firstInvocation.get(), secondInvocation.get());
        Mockito.verify(pricesRepository, Mockito.times(1))
                .findPricesByStartDateAndProductIdAndBrandId(LocalDateTime.parse(startDate), productId, brandId);
    }


    private static PricesDTO getPricesDTOMock(Long brandId, Long productId, String startDate) {
        return PricesDTO.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse(startDate))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();
    }

    private static Prices getPricesDomainMock(Long brandId, Long productId, String startDate) {
        return Prices.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse(startDate))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();
    }

    private static PricesEntity getPricesEntityMock(Long brandId, Long productId, String startDate) {
        return PricesEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse(startDate))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();
    }
}