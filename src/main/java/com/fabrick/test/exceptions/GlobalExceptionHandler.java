package com.fabrick.test.exceptions;

import com.fabrick.test.enums.ExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class handling all custom exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handler for a ResponseServerException
     * @param exception the ResponseServerException to be thrown
     * @return <code>ResponseEntity<ErrorResponse></code>
     */
    @ExceptionHandler(value = ResponseServerException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<ErrorResponse> handleServerValidationException(ResponseServerException exception){
        LOGGER.error("The current call failed maybe due to unsuccessful server validation", exception);
        ErrorResponse errorResponse = new ErrorResponse(ExceptionType.UNSUCCESSFUL.getMessage(), exception.getReason());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

    /**
     * Handler for a ResponseJsonProcessingException
     * @param exception the ResponseJsonProcessingException to be thrown
     * @return <code>ResponseEntity<ErrorResponse></code>
     */
    @ExceptionHandler(value = ResponseJsonProcessingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<ErrorResponse> handleJsonProcessingValidationException(ResponseJsonProcessingException exception){
        LOGGER.error("It was not possible to process the Json response", exception);
        ErrorResponse errorResponse = new ErrorResponse(ExceptionType.UNPROCESSABLE.getMessage(), exception.getReason());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

    /**
     * Handler for a ResponseJsonException
     * @param exception the ResponseJsonException to be thrown
     * @return <code>ResponseEntity<ErrorResponse></code>
     */
    @ExceptionHandler(value = ResponseJsonException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleJsonValidationException(ResponseJsonException exception){
        LOGGER.error("Error in the Json response", exception);
        ErrorResponse errorResponse = new ErrorResponse(ExceptionType.JSON_ERROR.getMessage(), exception.getReason());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

}
