package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for the Spring MVC controller responsible for presenting the BankTransaction REST API.
 */
@RestController
public interface BankTransactionController {

    /**
     * initBinder for use in interpreting Timestamp objects.
     * @param binder binding object used to properly interpret Timestamp objects.
     */
    @InitBinder
    void initBinder(WebDataBinder binder);

    /**
     * REST API endpoint for creating a transaction.
     * @param bankTransactionObj deserialized BankTransaction object to be added to the database.
     * @return ResponseEntity containing the BankTransaction object as it appears in the transactions table (i.e., with generated timestamp).
     */
    @PostMapping("/transactions")
    ResponseEntity<BankTransactionImpl> createTransaction(@Valid  @RequestBody BankTransactionImpl bankTransactionObj);

    /**
     * REST API endpoint for getting all transactions.
     * @return ResponseEntity containing a list of JSON objects representing all BankTransaction objects within the transactions table.
     */
    @GetMapping("/transactions")
    ResponseEntity<List<BankTransactionImpl>> getAllTransactions();

    /**
     * REST API endpoint for getting a specific transaction.
     * @param timestamp timestamp of the transaction to be retrieved, defined via the API endpoint.
     * @return ResponseEntity containing a JSON object representing the BankTransaction object as it appears in the transactions table.
     */
    @GetMapping("/transactions/{timestamp}")
    ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@Valid @PathVariable("timestamp") Timestamp timestamp);

    /**
     * REST API endpoint for updating the transaction method used in a previously-recorded transaction.
     * @param timestamp timestamp of the transaction for which the method will be updated, defined via the API endpoint.
     * @param transactionMethod String representing the new transaction method to be updated within the BankTransaction record.
     * @return ResponseEntity containing a JSON object representing the updated BankTransaction object as it appears in the transactions table.
     */
    @PutMapping("/transactions/{timestamp}/update")
    ResponseEntity<BankTransactionImpl> updateTransactionMethod(@Valid @PathVariable("timestamp") Timestamp timestamp, @Valid @RequestParam(value = "method") String transactionMethod);

    /* We have no delete mapping because this would mean we'd need to double-check whether the rest of the transactions are now valid.
     * This is obviously expensive at scale, so I think in the context of this project it's not unreasonable
     * to *not* do it as a design decision to demonstrate foresight.
     */
}
