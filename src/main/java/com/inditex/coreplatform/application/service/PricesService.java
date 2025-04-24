package com.inditex.coreplatform.application.service;

import com.inditex.coreplatform.application.service.dto.PricesDTO;

public interface PricesService {

    PricesDTO findPricesByStartDateAndProductIdAndBrandId(
            String startDate, Long productId, Long brandId);
}
