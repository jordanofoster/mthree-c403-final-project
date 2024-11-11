package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import com.jfoster.finalproject.service.BankCustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class implementation for the Spring MVC controller responsible for presenting the BankCustomer REST API.
 */
@RestController
public class BankCustomerControllerImpl implements BankCustomerController {

    /**
     * Autowired service bean that permits communication with the service layer of the BankCustomer MVC substack.
     */
    @Autowired
    private BankCustomerServiceImpl bankCustomerService;

    /**
     * REST API endpoint for creating a customer.
     * @param bankCustomerObj deserialized BankCustomer object to be added to the database.
     * @return ResponseEntity containing the new BankCustomer object as it appears in the customers table (i.e., with generated ID).
     */
    @Override
    public ResponseEntity<BankCustomerImpl> createCustomer(@Valid  @RequestBody BankCustomerImpl bankCustomerObj) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.createCustomer(bankCustomerObj), HttpStatus.CREATED);
    }

    /**
     * REST API endpoint for getting all customers.
     * @return ResponseEntity containing a list of JSON objects representing all BankCustomer objects within the customers table.
     */
    @Override
    public ResponseEntity<List<BankCustomerImpl>> getAllCustomers() {
        return new ResponseEntity<List<BankCustomerImpl>>(bankCustomerService.getAllCustomers(), HttpStatus.OK);
    }

    /**
     * REST API endpoint for getting a specific customer.
     * @param id ID of the bank customer to be retrieved, defined via the API endpoint.
     * @return ResponseEntity containing a JSON object representing the BankCustomer object as it appears in the customers table.
     */
    @Override
    public ResponseEntity<BankCustomerImpl> getCustomerById(@Valid @PathVariable("id") long id) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.getCustomerById(id), HttpStatus.OK);
    }

    /**
     * REST API endpoint for updating a specific customer record.
     * @param id Id of the bank customer to be updated, defined via the API endpoint.
     * @param updatedBankCustomerObj deserialized BankCustomer object to be referenced when updating the record.
     * @return ResponseEntity containing the updated BankCustomer object as it appears in the customers table.
     */
    @Override
    public ResponseEntity<BankCustomerImpl> updateCustomer(@Valid @PathVariable("id") long id, @RequestBody BankCustomerImpl updatedBankCustomerObj) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.updateCustomer(updatedBankCustomerObj, id), HttpStatus.OK);
    }

    /**
     * REST API endpoint for deleting an existing customer.
     * @param id Id of the customer to be deleted.
     */
    @Override
    public void deleteCustomerById(@Valid @PathVariable("id") long id) {
        bankCustomerService.deleteCustomerById(id);
    }
}
