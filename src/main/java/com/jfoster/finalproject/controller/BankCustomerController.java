package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface for the Spring MVC controller responsible for presenting the BankCustomer REST API.
 */
public interface BankCustomerController {


    /**
     * REST API endpoint for creating a customer.
     * @param bankCustomerObj deserialized BankCustomer object to be added to the database.
     * @return ResponseEntity containing the new BankCustomer object as it appears in the customers table (i.e., with generated ID).
     */
    @PostMapping("/customers")
    ResponseEntity<BankCustomerImpl> createCustomer(@Valid  @RequestBody BankCustomerImpl bankCustomerObj);

    /**
     * REST API endpoint for getting all customers.
     * @return ResponseEntity containing a list of JSON objects representing all BankCustomer objects within the customers table.
     */
    @GetMapping("/customers")
    ResponseEntity<List<BankCustomerImpl>> getAllCustomers();

    /**
     * REST API endpoint for getting a specific customer.
     * @param id ID of the bank customer to be retrieved, defined via the API endpoint.
     * @return ResponseEntity containing a JSON object representing the BankCustomer object as it appears in the customers table.
     */
    @GetMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> getCustomerById(@Valid @PathVariable("id") long id);

    /**
     * REST API endpoint for updating a specific customer record.
     * @param id Id of the bank customer to be updated, defined via the API endpoint.
     * @param updatedBankCustomerObj deserialized BankCustomer object to be referenced when updating the record.
     * @return ResponseEntity containing the updated BankCustomer object as it appears in the customers table.
     */
    @PutMapping("/customers/{id}")
    ResponseEntity<BankCustomerImpl> updateCustomer(@Valid @PathVariable("id") long id, @Valid @RequestBody BankCustomerImpl updatedBankCustomerObj);

    /**
     * REST API endpoint for deleting an existing customer.
     * @param id Id of the customer to be deleted.
     */
    @DeleteMapping("/customers/{id}")
    void deleteCustomerById(@Valid @PathVariable("id") long id);

}
