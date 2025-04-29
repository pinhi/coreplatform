package com.inditex.coreplatform.application.service.impl;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.domain.port.PricesRepository;
import com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper.PricesDomainDTOMapper;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import com.inditex.coreplatform.infrastructure.adapter.out.mapper.PricesDomainEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

        PricesEntity expectedEntity = PricesEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        Prices expectedDomain = Prices.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        PricesDTO expectedDto = PricesDTO.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

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
}