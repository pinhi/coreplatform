package com.inditex.coreplatform.infrastructure.adapter.in.rest.exceptionhandler;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HttpError {

    private int statusCode;

    private String errorCodeDescription;

    private String message;

    private String details;

    private String endpoint;

    private String sessionId;
}
