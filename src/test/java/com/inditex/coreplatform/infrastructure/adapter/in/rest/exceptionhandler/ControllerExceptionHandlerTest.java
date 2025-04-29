package com.inditex.coreplatform.infrastructure.adapter.in.rest.exceptionhandler;

import com.inditex.coreplatform.application.TestContextConfiguration;
import com.inditex.coreplatform.application.service.PricesService;
import com.inditex.coreplatform.infrastructure.adapter.in.rest.PricesController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PricesController.class)
@Import({TestContextConfiguration.class, ControllerExceptionHandler.class})
public class ControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PricesService pricesService;

    @Test
    void testHandleMethodArgumentTypeMismatchExceptionn() throws Exception {
        mockMvc.perform(get("/prices/")
                        .param("startDate", "2023-04-29T11:59:32")
                        .param("productId", "abc")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.statusCode", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("$.errorCodeDescription", is("Bad Request")))
                .andExpect(jsonPath("$.endpoint", is("/prices/")));
    }

    @Test
    void testHandleException_withRuntimeException() throws Exception {

        Mockito.when(pricesService.findPricesByStartDateAndProductIdAndBrandId(Mockito.anyString(), Mockito.anyLong(), Mockito.anyLong()))
                .thenThrow(new RuntimeException("Expected-Unexpected error"));

        mockMvc.perform(get("/prices/")
                        .param("startDate", "2023-04-29T11:59:32")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.statusCode", is(500)))
                .andExpect(jsonPath("$.message", is("Expected-Unexpected error")));
    }
}