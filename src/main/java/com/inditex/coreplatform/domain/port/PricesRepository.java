package com.inditex.coreplatform.domain.port;

import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;

public interface PricesRepository {

    PricesEntity findPricesByStartDateAndProductIdAndBrandId(
            String startDate, Long productId, Long brandId);
}
