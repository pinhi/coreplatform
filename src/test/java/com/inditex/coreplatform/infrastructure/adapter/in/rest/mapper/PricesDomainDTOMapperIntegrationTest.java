package com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PricesDomainDTOMapperIntegrationTest {

    @Autowired
    private PricesDomainDTOMapper mapper;

    @Test
    void testToDTO() {
        // given
        Prices domain = Prices.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        // when
        PricesDTO dto = mapper.toDTO(domain);

        // then
        assertEquals(domain.getBrandId(), dto.getBrandId());
        assertEquals(domain.getProductId(), dto.getProductId());
        assertEquals(domain.getStartDate(), dto.getStartDate());
        assertEquals(domain.getEndDate(), dto.getEndDate());
        assertEquals(domain.getPrice(), dto.getPrice());
    }

    @Test
    void testToDomain() {
        // given
        PricesDTO dto = PricesDTO.builder()
                .brandId(1L)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T18:00:00"))
                .price(new BigDecimal("35.50"))
                .build();

        // when
        Prices domain = mapper.toDomain(dto);

        // then
        assertEquals(dto.getBrandId(), domain.getBrandId());
        assertEquals(dto.getProductId(), domain.getProductId());
        assertEquals(dto.getStartDate(), domain.getStartDate());
        assertEquals(dto.getEndDate(), domain.getEndDate());
        assertEquals(dto.getPrice(), domain.getPrice());
    }

    @Test
    void testToDTO_NullInput() {
        // when
        PricesDTO dto = mapper.toDTO(null);

        // then
        assertNull(dto);
    }

    @Test
    void testToDomain_NullInput() {
        // when
        Prices domain = mapper.toDomain(null);

        // then
        assertNull(domain);
    }
}