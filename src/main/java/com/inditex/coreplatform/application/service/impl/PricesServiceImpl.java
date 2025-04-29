package com.inditex.coreplatform.application.service.impl;

import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import com.inditex.coreplatform.domain.port.PricesRepository;
import com.inditex.coreplatform.infrastructure.adapter.in.rest.mapper.PricesDomainDTOMapper;
import com.inditex.coreplatform.infrastructure.adapter.out.mapper.PricesDomainEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PricesServiceImpl implements PricesService {

    @Autowired
    private PricesDomainDTOMapper pricesDomainDTOMapper;

    @Autowired
    private PricesDomainEntityMapper pricesDomainEntityMapper;

    @Autowired
    private PricesRepository pricesRepository;

    @Override
    public Optional<PricesDTO> findPricesByStartDateAndProductIdAndBrandId(String startDate, Long productId, Long brandId) {
        return pricesRepository.findPricesByStartDateAndProductIdAndBrandId(LocalDateTime.parse(startDate), productId, brandId)
                .map(pricesDomainEntityMapper::toDomain)
                .map(pricesDomainDTOMapper::toDTO);
    }
}
