package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BankCustomerController {


    @PostMapping("/customers")
    ResponseEntity<BankCustomerImpl> createCustomer(@Valid  @RequestBody BankCustomerImpl bankCustomerObj);

    @GetMapping("/customers")
    ResponseEntity<List<BankCustomerImpl>> getAllCustomers();

    @GetMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> getCustomerById(@Valid @PathVariable("id") long id);

    @PutMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> updateCustomer(@Valid @PathVariable("id") long id, @Valid @RequestBody BankCustomerImpl updatedBankCustomerObj);

    @DeleteMapping("/customers/{id}")
    void deleteCustomerById(@Valid @PathVariable("id") long id);

}
