package com.inditex.coreplatform.infrastructure.adapter.out.mapper;
import com.inditex.coreplatform.domain.modal.Prices;
import com.inditex.coreplatform.infrastructure.adapter.out.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component("itemDocMapper")
public interface PricesDomainEntityMapper {

    Prices mapPricesEntityToPrices(PricesEntity entity);

    PricesEntity mapPricesToPricesEntity(Prices prices);
}
