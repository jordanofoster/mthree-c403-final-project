package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BankCustomerController {


    @PostMapping("/customers")
    ResponseEntity<BankCustomerImpl> createCustomer(@RequestBody BankCustomerImpl bankCustomerObj);

    @GetMapping("/customers")
    ResponseEntity<List<BankCustomerImpl>> getAllCustomers();

    @GetMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> getCustomerById(@PathVariable("id") long id);

    @PutMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> updateCustomer(@RequestBody BankCustomerImpl updatedBankCustomerObj, @PathVariable("id") long id);

    @DeleteMapping("/customers/{id}")
    void deleteCustomerById(@PathVariable("id") long id);

}
