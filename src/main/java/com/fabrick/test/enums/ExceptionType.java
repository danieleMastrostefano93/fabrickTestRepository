package com.fabrick.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum class for supplying custom error messages
 */
@Getter
@AllArgsConstructor
public enum ExceptionType {

    UNSUCCESSFUL("The call has not been successful maybe due to failing server validation"),

    UNPROCESSABLE("It was not possible to process the Json response"),
    JSON_ERROR("Error in the Json response");

    private final String message;
}
