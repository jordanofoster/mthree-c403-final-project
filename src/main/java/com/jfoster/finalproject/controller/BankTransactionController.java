package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public interface BankTransactionController {

    @InitBinder
    public void initBinder(WebDataBinder binder);

    @PostMapping("/transactions")
    ResponseEntity<BankTransactionImpl> createTransaction(@RequestBody BankTransactionImpl bankTransactionObj);

    @GetMapping("/transactions")
    ResponseEntity<List<BankTransactionImpl>> getAllTransactions();

    @GetMapping("/transactions/{timestamp}")
    ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp);

    @PutMapping("/transactions/{timestamp}")
    ResponseEntity<BankTransactionImpl> updateTransaction(@RequestBody BankTransactionImpl updatedBankTransactionObj, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp);

    @DeleteMapping("/transactions/{timestamp}")
    void deleteTransactionByTimestamp(@PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp);
}
