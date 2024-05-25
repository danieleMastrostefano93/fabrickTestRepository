package com.fabrick.test.controller;

import com.fabrick.test.dtos.BonificoDto;
import com.fabrick.test.service.RestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to consume Fabrick's APIs
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${local.path}")
public class Controller {

    @Autowired
    RestService service;

    /**
     * Method to consume Fabrick's API for obtaining balance of an account
     * @param accountId The id of the account to extract balance
     * @return The balance details of the specified account
     */
    @Operation(summary = "Returns account balance details for given accountId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the balance",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "422", description = "Impossible to process",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service errors",
                    content = @Content)})
    @GetMapping("${local.path.balance}")
    @ResponseBody
    public ResponseEntity<Object> getAccountBalance(@PathVariable Long accountId) {
        return new ResponseEntity<>(service.getAccountBalance(accountId), HttpStatus.OK);
    }

    /**
     * Method to consume Fabrick's API for making a payment for an account
     * @param accountId The id of the account to make payment
     * @param bonifico The Dto request object
     * @return The details of a successful payment related to the specified account
     */
    @Operation(summary = "Make a payment from the given account id along with the information being passed")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment done successfully",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "422", description = "Impossible to process",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service errors",
                    content = @Content)})
    @PostMapping("${local.path.payment}")
    @ResponseBody
    public ResponseEntity<Object> makeBonifico(@PathVariable Long accountId, @RequestBody @Valid BonificoDto bonifico) {
        return new ResponseEntity<>(service.makeBonifico(accountId, bonifico), HttpStatus.OK);
    }

    /**
     * Method to consume Fabrick's API for getting all the transactions related to the specific account and between selected dates
     * @param accountId The id of the account to make payment
     * @param fromAccountingDate The starting date to look for transactions
     * @param toAccountingDate The end date to look for transactions
     * @return A list of all the transactions related to the specific account and between selected dates
     */
    @Operation(summary = "Return a list of transactions for the given account id related to the specified period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions found",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "422", description = "Impossible to process",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content),
            @ApiResponse(responseCode = "503", description = "Service errors",
                    content = @Content),})
    @GetMapping("${local.path.transactions}")
    @ResponseBody
    public ResponseEntity<Object> getAccountTransactions(@PathVariable Long accountId,
                                                         @RequestParam() String fromAccountingDate,
                                                         @RequestParam() String toAccountingDate) {
        return new ResponseEntity<>(service.getAccountTransactions(accountId, fromAccountingDate, toAccountingDate), HttpStatus.OK);
    }
}
