package com.fabrick.test.utility.impl;

import com.fabrick.test.utility.interfaces.HeaderUtils;
import org.springframework.http.HttpHeaders;

/**
 * Implementation class of HeaderUtils interface
 */
public class HeaderUtilsImpl implements HeaderUtils {

        private static final String TIME_ZONE = "Europe/Rome";

        /**
         * Construct the header for making a rest call
         * @param authSchema The authorization schema
         * @param apiKey The Api Key
         * @return The header with specified parameters
         */
        public HttpHeaders getHeaders(String authSchema, String apiKey){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Auth-Schema", authSchema);
            httpHeaders.set("apiKey", apiKey);
            return httpHeaders;
        }

        /**
         * Construct the header for making a rest call
         * @param authSchema The authorization schema
         * @param apiKey The Api Key
         * @return The header with specified parameters and Europe/Rome time zone
         */
        public HttpHeaders getHeadersWithTimeZone(String authSchema, String apiKey) {
            HttpHeaders httpHeaders = getHeaders(authSchema, apiKey);
            httpHeaders.set("X-Time-Zone", TIME_ZONE);
            return httpHeaders;
        }

}
