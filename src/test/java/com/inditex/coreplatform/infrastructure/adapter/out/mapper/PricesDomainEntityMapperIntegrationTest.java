package com.inditex.coreplatform.infrastructure.adapter.out.mapper;

import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PricesDomainEntityMapperIntegrationTest {

    @Autowired
    private PricesDomainEntityMapper mapper;

    @Test
    void testToDomain() {
        // given
        PricesEntity entity = PricesEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        // when
        Prices domain = mapper.toDomain(entity);

        // then
        assertEquals(entity.getBrandId(), domain.getBrandId());
        assertEquals(entity.getProductId(), domain.getProductId());
        assertEquals(entity.getStartDate(), domain.getStartDate());
        assertEquals(entity.getEndDate(), domain.getEndDate());
        assertEquals(entity.getPrice(), domain.getPrice());
    }

    @Test
    void testToEntity() {
        // given
        Prices domain = Prices.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        // when
        PricesEntity entity = mapper.toEntity(domain);

        // then
        assertEquals(domain.getBrandId(), entity.getBrandId());
        assertEquals(domain.getProductId(), entity.getProductId());
        assertEquals(domain.getStartDate(), entity.getStartDate());
        assertEquals(domain.getEndDate(), entity.getEndDate());
        assertEquals(domain.getPrice(), entity.getPrice());
    }

    @Test
    void testToEntity_NullInput() {
        // when
        PricesEntity entity = mapper.toEntity(null);

        // then
        assertNull(entity);
    }

    @Test
    void testToDomain_NullInput() {
        // when
        Prices domain = mapper.toDomain(null);

        // then
        assertNull(domain);
    }
}