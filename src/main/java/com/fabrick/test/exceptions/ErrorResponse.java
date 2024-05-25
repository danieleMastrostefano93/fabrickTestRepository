package com.fabrick.test.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * The custom error response to be thrown
 */
@Data
public class ErrorResponse {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime timestamp;
        private String message;
        private String debugMessage;

        ErrorResponse(String message, String exceptionMessage) {
            timestamp = LocalDateTime.now();
            this.message = message;
            this.debugMessage = exceptionMessage;
        }
}

