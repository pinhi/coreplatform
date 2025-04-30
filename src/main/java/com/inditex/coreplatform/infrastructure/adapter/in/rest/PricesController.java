package com.inditex.coreplatform.infrastructure.adapter.in.rest;

import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.application.service.dto.PricesDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PricesController.class.getName());

    @Autowired
    private PricesService pricesService;

    @Operation(summary = "Get a price by start date, brand id and product id", description = "Returns a price with the selected start date, brand id and product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PricesDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found - The prices was not found", content = @Content(mediaType = "application/json"))
    })
    @CircuitBreaker(name = "pricesCircuitBreaker", fallbackMethod = "pricesFallback")
    @GetMapping("/")
    public ResponseEntity<PricesDTO> findPricesByStartDateAndProductIdAndBrandId(
            @Parameter(description = "Start date of the price", required = true, example = "2023-04-29T11:59:32")
            @RequestParam @DateTimeFormat(pattern = STANDARD_LOCAL_DATE_TIME_FORMAT) String startDate,
            @Parameter(description = "Product id related to the price", required = true, example = "451")
            @RequestParam Long productId,
            @Parameter(description = "Brand id related to the price", required = true, example = "1984")
            @RequestParam Long brandId) {
        return pricesService.findPricesByStartDateAndProductIdAndBrandId(startDate, productId, brandId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<PricesDTO> pricesFallback(String startDate, Long productId, Long brandId, Throwable throwable) {
        logger.warn("Fallback method executed for findPricesByStartDateAndProductIdAndBrandId with parameters startDate {}, productId {} and brandId {}", startDate, productId, brandId, throwable);
        return ResponseEntity.status(503).build();
    }
}
