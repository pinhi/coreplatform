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
        HttpError error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Error: " + ex.getMessage());
        error.setEndpoint(getEndpoint(request));
        error.setSessionId(request.getSessionId());
        logger.error("Detected in global exception handler: {}", error);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
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
