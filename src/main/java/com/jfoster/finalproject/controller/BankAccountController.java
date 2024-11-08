package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankAccountImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface BankAccountController {

    @PostMapping("/accounts")
    ResponseEntity<BankAccountImpl> createAccount(@RequestBody BankAccountImpl bankAccount);

    @GetMapping("/accounts")
    ResponseEntity<List<BankAccountImpl>> getAllAccounts();

    @GetMapping("/accounts/{id}")
    ResponseEntity<BankAccountImpl> getAccountById(@PathVariable("id") long id);

    @PutMapping("/accounts/{id}")
    ResponseEntity<BankAccountImpl> updateAccount(@RequestBody BankAccountImpl updatedBankAccountObj, @PathVariable("id") long id);

    @DeleteMapping("/accounts/{id}")
    void deleteAccountById(@PathVariable("id") long id);
}
