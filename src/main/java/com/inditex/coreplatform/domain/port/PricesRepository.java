package com.inditex.coreplatform.domain.port;

import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesRepository {

    Optional<PricesEntity> findPricesByStartDateAndProductIdAndBrandId(
            LocalDateTime startDate, Long productId, Long brandId);
}
