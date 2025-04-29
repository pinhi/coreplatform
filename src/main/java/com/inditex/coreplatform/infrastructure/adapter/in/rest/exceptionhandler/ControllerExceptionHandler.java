package com.inditex.coreplatform.infrastructure.adapter.in.rest.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice("com.inditex.coreplatform.infrastructure.adapter.in.rest")
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpError> handleGlobalException(Exception ex, WebRequest request) {
        HttpError httpError = HttpError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorCodeDescription(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .endpoint(getEndpoint(request))
                .sessionId(request.getSessionId())
                .build();
        logger.error("Detected error in controller exception handler", ex);
        return new ResponseEntity<>(httpError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getEndpoint(WebRequest request) {
        try {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        } catch (Exception e) {
            logger.error("Error getting values from WebRequest in ControllerExceptionHandler");
            return null;
        }
    }
}
