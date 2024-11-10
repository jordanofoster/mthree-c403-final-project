package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import com.jfoster.finalproject.service.BankTransactionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class BankTransactionControllerImpl implements BankTransactionController {


    @Autowired
    private BankTransactionServiceImpl bankTransactionService;

    @InitBinder
    @Override
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(new Timestamp(dateFormat.parse(text).getTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }

    @Override
    public ResponseEntity<BankTransactionImpl> createTransaction(@Valid  @RequestBody BankTransactionImpl bankTransactionObj) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.createTransaction(bankTransactionObj), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<BankTransactionImpl>> getAllTransactions() {
        return new ResponseEntity<List<BankTransactionImpl>>(bankTransactionService.getAllTransactions(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankTransactionImpl> getTransactionByTimestamp(@Valid @PathVariable("timestamp") Timestamp timestamp) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.getTransactionByTimestamp(timestamp), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankTransactionImpl> updateTransactionMethod(@Valid @PathVariable("timestamp") Timestamp timestamp, @Valid @RequestParam(value = "method") String transactionMethod) {
        return new ResponseEntity<BankTransactionImpl>(bankTransactionService.updateTransactionMethod(timestamp, transactionMethod), HttpStatus.OK);
    }
}
