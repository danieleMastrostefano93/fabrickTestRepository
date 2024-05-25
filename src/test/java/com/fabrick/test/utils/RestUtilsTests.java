package com.fabrick.test.utils;

import com.fabrick.test.utility.rest.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestUtilsTests {

    @Autowired
    RestUtils restUtils;

    public static String url;
    public static URI uri;
    public static URI baseUri;
    public static String completeUrl;
    public static String text;
    public static HashMap<String, String> params = new HashMap<>();

    @BeforeClass
    public static void setUp(){
        url = "http://md5.jsontest.com/";
        completeUrl = "http://md5.jsontest.com/?text=text";
        text = "text";
        params.put("text", text);
        uri = URI.create(completeUrl);
        baseUri = URI.create(url);
    }

    @Test
    public void fillUriTest(){
        Assert.assertEquals(restUtils.fillUri(url, new HashMap<>(), params), uri);
    }

    @Test
    public void fillUriTestNull(){
        Assert.assertEquals(restUtils.fillUri(url, new HashMap<>(), null), baseUri);
    }

    @Test
    public void executeRestCallTest() throws JsonProcessingException {
        Assert.assertNotNull(restUtils.executeRestCall(url, new HashMap<>(), params, HttpMethod.GET, new HttpEntity<>(new HttpHeaders())));
    }
}
