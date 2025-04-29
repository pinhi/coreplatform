package com.inditex.coreplatform.infrastructure.adapter.out.repository;

import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class H2PricesRepositoryIntegrationTest {

    @Autowired
    private H2PricesRepository repository;

    @Test
    void testFindPricesByStartDateAndProductIdAndBrandId() {
        // given
        PricesEntity entity = PricesEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T23:59:59"))
                .price(new BigDecimal("35.50"))
                .priceList(1)
                .priority(1)
                .currency("EUR")
                .build();
        repository.save(entity);

        // when
        Optional<PricesEntity> result = repository.findPricesByStartDateAndProductIdAndBrandId(entity.getStartDate(), entity.getProductId(), entity.getBrandId());

        // then
        assertTrue(result.isPresent());
        assertEquals(entity.getStartDate(), result.get().getStartDate());
        assertEquals(entity.getProductId(), result.get().getProductId());
        assertEquals(entity.getBrandId(), result.get().getBrandId());
        assertEquals(entity.getPrice(), result.get().getPrice());
    }
}