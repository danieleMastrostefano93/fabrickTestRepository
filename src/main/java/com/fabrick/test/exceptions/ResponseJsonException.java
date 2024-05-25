package com.fabrick.test.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

/**
 * Exception to be thrown when error occurring in JSON response
 */
public class ResponseJsonException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResponseJsonException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
