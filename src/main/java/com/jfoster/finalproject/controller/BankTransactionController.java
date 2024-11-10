package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public interface BankTransactionController {

    @InitBinder
    void initBinder(WebDataBinder binder);

    @PostMapping("/transactions")
    ResponseEntity<BankTransactionImpl> createTransaction(@Valid  @RequestBody BankTransactionImpl bankTransactionObj);

    @GetMapping("/transactions")
    ResponseEntity<List<BankTransactionImpl>> getAllTransactions();

    @GetMapping("/transactions/{timestamp}")
    ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@Valid @PathVariable("timestamp") Timestamp timestamp);

    @PutMapping("/transactions/{timestamp}/update")
    ResponseEntity<BankTransactionImpl> updateTransactionMethod(@Valid @PathVariable("timestamp") Timestamp timestamp, @Valid @RequestParam(value = "method") String transactionMethod);

    // We have no delete mapping because this would mean we'd need to double-check whether the rest of the transactions are now valid.
    // This is obviously expensive at scale, so I think in the context of this project it's not unreasonable
    // to /not/ do it as a design decision to demonstrate foresight.
}
