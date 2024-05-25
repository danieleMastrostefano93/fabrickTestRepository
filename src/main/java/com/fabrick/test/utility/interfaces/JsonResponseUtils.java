package com.fabrick.test.utility.interfaces;

import com.fabrick.test.entities.TransactionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

/**
 * Interface for methods dealing with Json response content
 */
public interface JsonResponseUtils {

    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method to extract some specific information related to Fabrick Json response structure
     * @param response The response got after consuming Fabrick's API
     * @return List of Transaction entities
     */
    static List<TransactionEntity> extractTransactionsFromResponse(Object response) throws JSONException, JsonProcessingException {
        JSONObject content = new JSONObject(response.toString());
        content = new JSONObject(content.get("payload").toString());
        JSONArray contentList = new JSONArray(content.get("list").toString());
        return mapper.readValue(contentList.toString(), new TypeReference<>() {
        });
    }

}
