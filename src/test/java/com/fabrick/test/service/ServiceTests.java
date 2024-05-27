package com.fabrick.test.service;

import com.fabrick.test.configuration.ApiConfig;
import com.fabrick.test.dtos.BonificoDto;
import com.fabrick.test.exceptions.ResponseJsonException;
import com.fabrick.test.exceptions.ResponseJsonProcessingException;
import com.fabrick.test.exceptions.ResponseServerException;
import com.fabrick.test.utility.rest.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

    @MockBean
    ApiConfig apiConfig;

    @MockBean
    RestUtils restUtils;

    @Autowired
    RestService service;

    @MockBean
    TransactionRepository repository;

    private static Long accountId;
    private static String test;
    private static String testUrl;
    private static String response;

    @BeforeClass
    public static void setUp(){
        accountId = 1L;
        test = "test";
        testUrl = "https://google.com";
        response = "{payload: {list: []}}";
    }

    @Test
    public void getAccountBalanceTest() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getBalanceUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenReturn(new Object());
        Assert.assertNotNull(service.getAccountBalance(accountId));
    }

    @Test
    public void getAccountBalanceTestServerEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getBalanceUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(HttpClientErrorException.class);
        ResponseServerException exception = Assert.assertThrows(ResponseServerException.class, () -> service.getAccountBalance(accountId));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void getAccountBalanceTestJsonEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getBalanceUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(JsonProcessingException.class);
        ResponseJsonProcessingException exception = Assert.assertThrows(ResponseJsonProcessingException.class, () -> service.getAccountBalance(accountId));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void makeBonificoTest() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getPaymentUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenReturn(new Object());
        Assert.assertNotNull(service.makeBonifico(accountId, new BonificoDto()));
    }

    @Test
    public void makeBonificoTestServerEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getPaymentUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(HttpClientErrorException.class);
        ResponseServerException exception = Assert.assertThrows(ResponseServerException.class, () -> service.makeBonifico(accountId, new BonificoDto()));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void makeBonificoTestJsonProcessingEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getPaymentUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(JsonProcessingException.class);
        ResponseJsonProcessingException exception = Assert.assertThrows(ResponseJsonProcessingException.class, () -> service.makeBonifico(accountId, new BonificoDto()));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void getAccountTransactionsTest() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getTransactionsUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenReturn(response);
        Mockito.when(repository.saveAll(anyList())).thenReturn(new ArrayList<>());
        Assert.assertNotNull(service.getAccountTransactions(accountId, test, test));
    }

    @Test
    public void getAccountTransactionsTestServerEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getTransactionsUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(HttpClientErrorException.class);
        ResponseServerException exception = Assert.assertThrows(ResponseServerException.class, () -> service.getAccountTransactions(accountId, test, test));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Test
    public void getAccountTransactionsTestJsonProcessingEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getTransactionsUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenThrow(JsonProcessingException.class);
        ResponseJsonProcessingException exception = Assert.assertThrows(ResponseJsonProcessingException.class, () -> service.getAccountTransactions(accountId, test, test));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void getAccountTransactionsTestJsonEx() throws JsonProcessingException {
        Mockito.when(apiConfig.getAuthSchema()).thenReturn(test);
        Mockito.when(apiConfig.getApiKey()).thenReturn(test);
        Mockito.when(apiConfig.getTransactionsUrl()).thenReturn(testUrl);
        Mockito.when(restUtils.executeRestCall(any(),any(),any(),any(),any())).thenReturn(new Object());
        ResponseJsonException exception = Assert.assertThrows(ResponseJsonException.class, () -> service.getAccountTransactions(accountId, test, test));
        Assert.assertEquals(exception.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
