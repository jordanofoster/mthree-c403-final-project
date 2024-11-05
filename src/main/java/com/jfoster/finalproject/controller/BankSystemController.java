package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public interface BankSystemController {

    // Create
    @PostMapping("/customers")
    BankCustomer addCustomer(@RequestBody BankCustomer bankCustomerObj);

    @PostMapping("/accounts")
    BankAccount addAccount(@RequestBody BankAccount bankAccountObj);

    @PostMapping("/transactions")
    BankTransaction addTransaction(@RequestBody BankTransaction bankTransactionObj);

    // Read
    @GetMapping("/customers")
    ResponseEntity<List<BankCustomer>> getCustomers();

    @GetMapping("/customers/{id}")
    BankCustomer getCustomerById(@PathVariable("id") int id);

    @GetMapping("/accounts")
    ResponseEntity<List<BankAccount>> getAccounts();

    @GetMapping("/accounts/{id}")
    BankAccount getAccountById(@PathVariable("id") int id);

    @GetMapping("/transactions")
    ResponseEntity<List<BankTransaction>> getTransactions();

    @GetMapping("/transactions/{timestamp}")
    BankTransaction getTransactionByTimestamp(@PathVariable("timestamp") LocalDateTime timestamp);

    // Update
    @PutMapping("/customers/{id}")
    void updateCustomer(@RequestBody BankCustomer updatedBankCustomerObj, @PathVariable("id") int id);

    @PutMapping("/accounts/{id}")
    void updateAccount(@RequestBody BankAccount updatedBankAccountObj, @PathVariable("id") int id);

    @PutMapping("/transactions/{timestamp}")
    void updateTransaction(@RequestBody BankTransaction updatedBankTransactionObj, @PathVariable("timestamp") LocalDateTime timestamp);

    // Delete
    @DeleteMapping("/customers/{id}")
    void deleteCustomerById(@PathVariable("id") int id);

    @DeleteMapping("/accounts/{id}")
    void deleteAccountById(@PathVariable("id") int id);

    @DeleteMapping("/transactions/{timestamp}")
    void deleteTransactionById(@PathVariable("timestamp") LocalDateTime timestamp);

}
