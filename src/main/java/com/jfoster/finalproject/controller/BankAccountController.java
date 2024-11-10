package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankAccountImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public interface BankAccountController {

    @PostMapping("/accounts")
    ResponseEntity<BankAccountImpl> createAccount(@RequestBody @Valid BankAccountImpl bankAccount);

    @GetMapping("/accounts")
    ResponseEntity<List<BankAccountImpl>> getAllAccounts();

    @GetMapping("/accounts/{id}")
    ResponseEntity<BankAccountImpl> getAccountById(@PathVariable("id") @Valid long id);

    @PutMapping("/accounts/{id}/debit")
    ResponseEntity<BigDecimal> debit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount);

    @PutMapping("/accounts/{id}/credit")
    ResponseEntity<BigDecimal> credit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount);

    @PutMapping("/accounts/{id}/update")
    ResponseEntity<BigDecimal> updateMaxOverdraft(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "overdraft") BigDecimal overdraft);

    @DeleteMapping("/accounts/{id}")
    void deleteAccountById(@Valid @PathVariable("id") long id);
}
