package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import com.jfoster.finalproject.service.BankCustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankCustomerControllerImpl implements BankCustomerController {

    @Autowired
    private BankCustomerServiceImpl bankCustomerService;

    @Override
    public ResponseEntity<BankCustomerImpl> createCustomer(@Valid  @RequestBody BankCustomerImpl bankCustomerObj) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.createCustomer(bankCustomerObj), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<BankCustomerImpl>> getAllCustomers() {
        return new ResponseEntity<List<BankCustomerImpl>>(bankCustomerService.getAllCustomers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankCustomerImpl> getCustomerById(@Valid @PathVariable("id") long id) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.getCustomerById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankCustomerImpl> updateCustomer(@Valid @PathVariable("id") long id, @RequestBody BankCustomerImpl updatedBankCustomerObj) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerService.updateCustomer(updatedBankCustomerObj, id), HttpStatus.OK);
    }

    @Override
    public void deleteCustomerById(@Valid @PathVariable("id") long id) {
        bankCustomerService.deleteCustomerById(id);
    }
}
