package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dao.BankTransactionRepository;
import com.jfoster.finalproject.dto.BankTransactionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class BankTransactionControllerImpl implements BankTransactionController {


    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @InitBinder
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new TimestampEditor());
    }

    public ResponseEntity<BankTransactionImpl> createTransaction(@RequestBody BankTransactionImpl bankTransactionObj) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionRepository.saveAndFlush(bankTransactionObj), HttpStatus.CREATED);
    }

    public ResponseEntity<List<BankTransactionImpl>> getAllTransactions() {
        return ResponseEntity.ok(bankTransactionRepository.findAll());
    }

    public ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@PathVariable("timestamp") Timestamp timestamp) {
        BankTransactionImpl transaction = bankTransactionRepository.findById(timestamp).orElse(null);
        return new ResponseEntity<BankTransactionImpl>(transaction, HttpStatus.OK);
    }

    public ResponseEntity<BankTransactionImpl> updateTransaction(@RequestBody BankTransactionImpl updatedBankTransactionObj, @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp) {
        if (!timestamp.equals(updatedBankTransactionObj.getTransactionTimestamp())) {
            return new ResponseEntity<BankTransactionImpl>(HttpStatus.BAD_REQUEST);
        } else {
            bankTransactionRepository.saveAndFlush(updatedBankTransactionObj);
            return new ResponseEntity<BankTransactionImpl>(updatedBankTransactionObj, HttpStatus.OK);
        }
    }

    public void deleteTransactionByTimestamp(@PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Timestamp timestamp) {
        bankTransactionRepository.deleteById(timestamp);
    }
}
