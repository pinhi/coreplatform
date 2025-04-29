package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.inditex.coreplatform.common.utils.Constants.STANDARD_LOCAL_DATE_TIME_FORMAT;

@RestController
@RequestMapping("/prices")
public class PricesController {

    @Autowired
    private PricesService pricesService;

    @GetMapping("/")
    public ResponseEntity<PricesDTO> findPricesByStartDateAndProductIdAndBrandId(
            @RequestParam(required = true) @DateTimeFormat(pattern = STANDARD_LOCAL_DATE_TIME_FORMAT) String startDate,
            @RequestParam(required = true) Long productId,
            @RequestParam(required = true) Long brandId) {
        return pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
