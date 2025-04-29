package com.inditex.coreplatform.application.service;

import com.inditex.coreplatform.application.service.dto.PricesDTO;

import java.util.Optional;

public interface PricesService {

    Optional<PricesDTO> findPricesByStartDateAndProductIdAndBrandId(
            String startDate, Long productId, Long brandId);
}
