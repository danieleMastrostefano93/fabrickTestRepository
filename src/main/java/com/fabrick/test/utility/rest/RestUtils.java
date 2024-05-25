package com.fabrick.test.utility.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

/**
 * Utility class for making rest calls
 */
@Component
public class RestUtils {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Execute a rest call from the given url and for the required method after setting url params and headers
     * @param url The url of the rest call
     * @param pathParams The path params of the rest call
     * @param queryParams The query params of the rest call
     * @param httpMethod Rest call method
     * @param httpEntity The headers entity
     * @return The response of the rest call
     * @throws JsonProcessingException The exception thrown
     */

    public Object executeRestCall(String url, HashMap<String, String> pathParams, HashMap<String, String> queryParams, HttpMethod httpMethod, HttpEntity<?> httpEntity) throws JsonProcessingException {
        URI uri = fillUri(url, pathParams, queryParams);
        String response = restTemplate.exchange(uri, httpMethod, httpEntity, String.class).getBody();
        return objectMapper.readValue(response, Object.class);
    }

    /**
     * Fill the uri con given parameters
     * @param url The url of the rest call
     * @param pathParams The path params of the rest call
     * @param queryParams The query params of the rest call
     * @return The complete uri
     */

    public URI fillUri(String url, HashMap<String, String> pathParams, HashMap<String, String> queryParams) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        if(queryParams!=null)
            queryParams.forEach(builder::queryParam);
        return builder.buildAndExpand(pathParams).toUri();
    }
}
