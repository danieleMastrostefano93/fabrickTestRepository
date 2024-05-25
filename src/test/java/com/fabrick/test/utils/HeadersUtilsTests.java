package com.fabrick.test.utils;

import com.fabrick.test.utility.impl.HeaderUtilsImpl;
import com.fabrick.test.utility.interfaces.HeaderUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadersUtilsTests {

    public static String test;
    public static HttpHeaders httpHeaders = new HttpHeaders();
    public static HttpHeaders httpHeadersWithTimeZone = new HttpHeaders();
    public HeaderUtils headerUtils = new HeaderUtilsImpl();
    public static HttpEntity<String> httpEntity;

    @BeforeClass
    public static void setUp(){
        test = "test";
        httpHeaders.set("Auth-Schema", "test");
        httpHeaders.set("apiKey", "test");
        httpHeadersWithTimeZone.addAll(httpHeaders);
        httpHeadersWithTimeZone.set("X-Time-Zone", "Europe/Rome");
        httpEntity = new HttpEntity<>(httpHeaders);
    }

    @Test
    public void getHeadersTest(){
        Assert.assertEquals(headerUtils.getHeaders(test, test), httpHeaders);
    }

    @Test
    public void getHeadersWithTimeZoneTest(){
        Assert.assertEquals(headerUtils.getHeadersWithTimeZone(test, test), httpHeadersWithTimeZone);
    }

    @Test
    public void getHttpEntityTest() {
        Assert.assertEquals(headerUtils.getHttpEntity(test, test), httpEntity);
    }
}
