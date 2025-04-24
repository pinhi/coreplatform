package com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;

//@Mapper(componentModel = "spring")
public interface PricesDomainDTOMapper {

    PricesDTO mapPricesToPricesDTO(Prices domain);

    Prices mapPricesDTOToPrices(PricesDTO dto);
}
