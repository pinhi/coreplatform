package com.inditex.coreplatform.infrastructure.adapter.in.rest.exceptionhandler;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class HttpError {

    // needed annotation for constructor
    @NonNull
    private int statusCode;

    @NonNull
    private String errorCodeDescription;

    @NonNull
    private String message;

    private String details;

    private String endpoint;

    private String sessionId;
}
