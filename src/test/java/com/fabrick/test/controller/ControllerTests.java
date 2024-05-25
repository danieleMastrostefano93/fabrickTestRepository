package com.fabrick.test.controller;

import com.fabrick.test.dtos.BonificoDto;
import com.fabrick.test.exceptions.ResponseJsonException;
import com.fabrick.test.exceptions.ResponseJsonProcessingException;
import com.fabrick.test.exceptions.ResponseServerException;
import com.fabrick.test.service.RestService;
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

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {

    @MockBean
    RestService service;

    @Autowired
    Controller controller;

    private static Long accountId;
    private static String test;

    @BeforeClass
    public static void setUp(){
        accountId = 1L;
        test = "test";
    }

    @Test
    public void getAccountBalanceTest(){
        Mockito.when(service.getAccountBalance(any())).thenReturn(new Object());
        Assert.assertEquals(controller.getAccountBalance(accountId).getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResponseServerException.class)
    public void getAccountBalanceTestServerEx(){
        Mockito.when(service.getAccountBalance(any())).thenThrow(ResponseServerException.class);
        controller.getAccountBalance(accountId);
    }

    @Test(expected = ResponseJsonProcessingException.class)
    public void getAccountBalanceTestJsonEx(){
        Mockito.when(service.getAccountBalance(any())).thenThrow(ResponseJsonProcessingException.class);
        controller.getAccountBalance(accountId);
    }

    @Test
    public void makeBonificoTest(){
        Mockito.when(service.makeBonifico(any(), any())).thenReturn(new Object());
        Assert.assertEquals(controller.makeBonifico(accountId, new BonificoDto()).getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResponseServerException.class)
    public void makeBonificoTestServerEx(){
        Mockito.when(service.makeBonifico(any(), any())).thenThrow(ResponseServerException.class);
        controller.makeBonifico(accountId, new BonificoDto());
    }

    @Test(expected = ResponseJsonProcessingException.class)
    public void makeBonificoTestJsonEx(){
        Mockito.when(service.makeBonifico(any(), any())).thenThrow(ResponseJsonProcessingException.class);
        controller.makeBonifico(accountId, new BonificoDto());
    }

    @Test
    public void getAccountTransactionsTest(){
        Mockito.when(service.getAccountTransactions(any(), any(), any())).thenReturn(new Object());
        Assert.assertEquals(controller.getAccountTransactions(accountId, test, test).getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResponseServerException.class)
    public void getAccountTransactionsTestServerEx(){
        Mockito.when(service.getAccountTransactions(any(), any(), any())).thenThrow(ResponseServerException.class);
        controller.getAccountTransactions(accountId, test, test);
    }

    @Test(expected = ResponseJsonProcessingException.class)
    public void getAccountTransactionsTestJsonProcessingEx(){
        Mockito.when(service.getAccountTransactions(any(), any(), any())).thenThrow(ResponseJsonProcessingException.class);
        controller.getAccountTransactions(accountId, test, test);
    }

    @Test(expected = ResponseJsonException.class)
    public void getAccountTransactionsTestJsonEx(){
        Mockito.when(service.getAccountTransactions(any(), any(), any())).thenThrow(ResponseJsonException.class);
        controller.getAccountTransactions(accountId, test, test);
    }

}
