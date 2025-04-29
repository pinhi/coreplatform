package com.inditex.coreplatform.infrastructure.adapter.out.mapper;

import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesDomainEntityMapper {

    Prices toDomain(PricesEntity entity);

    PricesEntity toEntity(Prices domain);
}
