package com.inditex.coreplatform.application.service.impl;

import com.inditex.coreplatform.TestCacheConfig;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.domain.port.PricesRepository;
import com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper.PricesDomainDTOMapper;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import com.inditex.coreplatform.infrastructure.adapter.out.mapper.PricesDomainEntityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestCacheConfig.class)
public class PricesServiceImplIntegrationTest {


    @Autowired
    private PricesServiceImpl pricesService;

    @Autowired
    private PricesRepository pricesRepository;

    @Test
    void testCacheBehavior() {
        // given
        String startDate = "2020-06-15T16:00:00";
        Long productId = 35455L;
        Long brandId = 1L;

        PricesEntity mockEntity = PricesEntity.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(LocalDateTime.parse(startDate))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        when(pricesRepository.findPricesByStartDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(Optional.of(mockEntity));

        // when
        Optional<PricesDTO> firstCall = pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);
        Optional<PricesDTO> secondCall = pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId);

        // then
        Assertions.assertTrue(firstCall.isPresent());
        Assertions.assertTrue(secondCall.isPresent());
        Assertions.assertSame(firstCall.get(), secondCall.get());
        Mockito.verify(pricesRepository, times(1))
                .findPricesByStartDateAndProductIdAndBrandId(LocalDateTime.parse(startDate), productId, brandId);
    }
}
