package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dao.BankCustomerRepository;
import com.jfoster.finalproject.dto.BankCustomerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankCustomerControllerImpl implements BankCustomerController {

    @Autowired
    private BankCustomerRepository bankCustomerRepository;

    public ResponseEntity<BankCustomerImpl> createCustomer(@RequestBody BankCustomerImpl bankCustomerObj) {
        return new ResponseEntity<BankCustomerImpl>(bankCustomerRepository.saveAndFlush(bankCustomerObj), HttpStatus.CREATED);
    }

    public ResponseEntity<List<BankCustomerImpl>> getAllCustomers() {
        return ResponseEntity.ok(bankCustomerRepository.findAll());
    }

    public ResponseEntity<BankCustomerImpl> getCustomerById(@PathVariable("id") long id) {
        BankCustomerImpl customer = bankCustomerRepository.findById(id).orElse(null);
        return new ResponseEntity<BankCustomerImpl>(customer, HttpStatus.OK);
    }

    public ResponseEntity<BankCustomerImpl> updateCustomer(@RequestBody BankCustomerImpl updatedBankCustomerObj, @PathVariable("id") long id) {
        if (id != updatedBankCustomerObj.getCustomerReferenceNumber()) {
            return new ResponseEntity<BankCustomerImpl>(HttpStatus.BAD_REQUEST);
        } else {
            bankCustomerRepository.saveAndFlush(updatedBankCustomerObj);
            return new ResponseEntity<BankCustomerImpl>(updatedBankCustomerObj, HttpStatus.OK);
        }
    }

    public void deleteCustomerById(@PathVariable("id") long id) {
        bankCustomerRepository.deleteById(id);
    }
}
