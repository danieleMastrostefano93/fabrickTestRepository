package com.fabrick.test.utility.interfaces;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for service methods
 */
public interface HeaderUtils {

    /**
     * Construct the header entity for making rest call
     * @param authSchema The authorization schema
     * @param apiKey The Api Key
     * @return Headers entity
     */
    default HttpEntity<String> getHttpEntity(String authSchema, String apiKey) {
        HttpHeaders httpHeaders = getHeaders(authSchema, apiKey);
        return new HttpEntity<>(httpHeaders);
    }

    /**
     * Construct the header for making a rest call
     * @param authSchema The authorization schema
     * @param apiKey The Api Key
     * @return The header with specified parameters
     */
    HttpHeaders getHeaders(String authSchema, String apiKey);

    /**
     * Construct the header for making a rest call
     * @param authSchema The authorization schema
     * @param apiKey The Api Key
     * @return The header with specified parameters and Europe/Rome time zone
     */
    HttpHeaders getHeadersWithTimeZone(String authSchema, String apiKey);

}
