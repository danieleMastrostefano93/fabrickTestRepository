package com.fabrick.test.utils;

import com.fabrick.test.utility.interfaces.JsonResponseUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonResponseUtilsTests {

    private static String jsonObject;

    @BeforeClass
    public static void setUp(){
        jsonObject = "{payload : {list : []}}";
    }

    @Test
    public void extractTransactionsFromResponseTest() throws JSONException, JsonProcessingException {
        Assert.assertNotNull(JsonResponseUtils.extractTransactionsFromResponse(jsonObject));
    }

    @Test
    public void extractTransactionsFromResponseTestEx() {
        Assert.assertThrows(JSONException.class, () -> JsonResponseUtils.extractTransactionsFromResponse(new Object()));
    }

}
