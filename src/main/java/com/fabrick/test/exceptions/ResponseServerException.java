package com.fabrick.test.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;

/**
 * Exception to be thrown when error occurring in server
 */
public class ResponseServerException extends ResponseStatusException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResponseServerException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
