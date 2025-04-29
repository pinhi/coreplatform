package com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper;

import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.modal.Prices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesDomainDTOMapper {

    PricesDTO toDTO(Prices domain);

    Prices toDomain(PricesDTO dto);
}
