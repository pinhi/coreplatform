package com.inditex.coreplatform.application.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricesDTO {

    private Long brandId;

    private Long productId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal price;
}
