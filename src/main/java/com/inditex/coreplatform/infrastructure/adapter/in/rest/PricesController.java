package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping("/")
    public ResponseEntity<PricesDTO> findPricesByStartDateAndProductIdAndBrandId(
            String startDate, Long productId, Long brandId) {
        return null;
    }
}
