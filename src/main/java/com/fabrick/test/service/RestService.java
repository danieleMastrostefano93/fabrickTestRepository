package com.fabrick.test.service;

import com.fabrick.test.configuration.ApiConfig;
import com.fabrick.test.dtos.BonificoDto;
import com.fabrick.test.exceptions.ResponseJsonException;
import com.fabrick.test.exceptions.ResponseJsonProcessingException;
import com.fabrick.test.exceptions.ResponseServerException;
import com.fabrick.test.repository.TransactionRepository;
import com.fabrick.test.utility.impl.HeaderUtilsImpl;
import com.fabrick.test.utility.interfaces.HeaderUtils;
import com.fabrick.test.utility.interfaces.JsonResponseUtils;
import com.fabrick.test.utility.rest.RestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;

/**
 * Service for the rest controller
 */
@Service
public class RestService {

    @Autowired
    ApiConfig apiConfig;

    @Autowired
    RestUtils restUtils;

    @Autowired
    TransactionRepository repository;

    private final HeaderUtils utils = new HeaderUtilsImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(RestService.class);
    private final static String ACCOUNT_ID = "accountId";
    private final static String FROM_ACCOUNTING_DATE = "fromAccountingDate";
    private final static String TO_ACCOUNTING_DATE = "toAccountingDate";

    /**
     * Method to consume through a rest call Fabrick API: <a href="https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0">Lettura Saldo</a>
     * @param accountId the id of the account to retrieve balance from
     * @return Balance details of the specified account
     */
    public Object getAccountBalance(Long accountId) {
        LOGGER.info("Start process: getAccountBalance");
        long start = System.currentTimeMillis();
        Object response;
        HttpEntity<String> httpEntity = utils.getHttpEntity(apiConfig.getAuthSchema(), apiConfig.getApiKey());
        String url = apiConfig.getBalanceUrl();
        HashMap<String,String> pathParams = new HashMap<>();
        pathParams.put(ACCOUNT_ID, accountId.toString());
        try{
            response = restUtils.executeRestCall(url, pathParams, null, HttpMethod.GET, httpEntity);
            long stop = System.currentTimeMillis();
            LOGGER.info("Stop process: getAccountBalance {( " + (stop - start) + " )} ");
        } catch (HttpClientErrorException ex) {
            long stop = System.currentTimeMillis();
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            LOGGER.info("Stop process: getAccountBalance {( " + (stop - start) + " )} ");
            throw new ResponseServerException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        } catch (JsonProcessingException ex){
            long stop = System.currentTimeMillis();
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            LOGGER.info("Stop process: getAccountBalance {( " + (stop - start) + " )} ");
            throw new ResponseJsonProcessingException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        }
        return response;
    }

    /**
     * Method to consume through a rest call Fabrick API: <a href="https://docs.fabrick.com/platform/apis/gbs-banking-payments-moneyTransfers-v4.0">Bonifico</a>
     * @param accountId the id of the account to make a payment to
     * @param bonifico Dto object for making payment
     * @return Payment details for the specified account
     */

    public Object makeBonifico(Long accountId, BonificoDto bonifico) {
        LOGGER.info("Start process: makeBonifico");
        long start = System.currentTimeMillis();
        Object response;
        HttpHeaders httpHeaders = utils.getHeadersWithTimeZone(apiConfig.getAuthSchema(), apiConfig.getApiKey());
        HttpEntity<Object> request = new HttpEntity<>(bonifico, httpHeaders);
        String url = apiConfig.getPaymentUrl();
        HashMap<String,String> pathParams = new HashMap<>();
        pathParams.put(ACCOUNT_ID, accountId.toString());
        try{
            response = restUtils.executeRestCall(url, pathParams, null, HttpMethod.POST, request);
            long stop = System.currentTimeMillis();
            LOGGER.info("Stop process: makeBonifico {( " + (stop - start) + " )} ");
        } catch (HttpClientErrorException ex){
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            long stop = System.currentTimeMillis();
            LOGGER.info("Stop process: makeBonifico {( " + (stop - start) + " )} ");
            throw new ResponseServerException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        } catch (JsonProcessingException ex){
            long stop = System.currentTimeMillis();
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            LOGGER.info("Stop process: makeBonifico {( " + (stop - start) + " )} ");
            throw new ResponseJsonProcessingException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        }
        return response;
    }

    /**
     * Method to consume through a rest call Fabrick API: <a href="https://docs.fabrick.com/platform/apis/gbs-banking-account-cash-v4.0">Lettura Transazioni</a>
     * @param accountId the id of the account to retrieve transactions from
     * @param fromAccountingDate The starting date of selected list of transactions
     * @param toAccountingDate The end date of selected list of transactions
     * @return List of transactions for the specified account into the selected dates
     */
    public Object getAccountTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
        LOGGER.info("Start process: getAccountTransactions");
        long start = System.currentTimeMillis();
        Object response;
        HttpEntity<String> httpEntity = utils.getHttpEntity(apiConfig.getAuthSchema(), apiConfig.getApiKey());
        String url = apiConfig.getTransactionsUrl();
        HashMap<String,String> pathParams = new HashMap<>();
        pathParams.put(ACCOUNT_ID, accountId.toString());
        HashMap<String,String> queryParams = new HashMap<>();
        queryParams.put(FROM_ACCOUNTING_DATE, fromAccountingDate);
        queryParams.put(TO_ACCOUNTING_DATE, toAccountingDate);
        try {
            response = restUtils.executeRestCall(url, pathParams, queryParams, HttpMethod.GET, httpEntity);
            repository.saveAll(JsonResponseUtils.extractTransactionsFromResponse(response));
            long stop = System.currentTimeMillis();
            LOGGER.info("Stop process: getAccountTransactions {( " + (stop - start) + " )} ");
        } catch (HttpClientErrorException ex) {
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            long stop = System.currentTimeMillis();
            LOGGER.info("Stop process: getAccountTransactions {( " + (stop - start) + " )} ");
            throw new ResponseServerException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        } catch (JsonProcessingException ex){
            long stop = System.currentTimeMillis();
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            LOGGER.info("Stop process: getAccountTransactions {( " + (stop - start) + " )} ");
            throw new ResponseJsonProcessingException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        } catch (JSONException ex) {
            long stop = System.currentTimeMillis();
            LOGGER.error("Following exception was thrown: " + ex.getMessage());
            LOGGER.info("Stop process: getAccountTransactions {( " + (stop - start) + " )} ");
            throw new ResponseJsonException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
        return response;
    }
}
