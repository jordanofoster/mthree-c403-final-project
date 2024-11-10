package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.service.BankAccountServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankAccountControllerImpl implements BankAccountController {

    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @Override
    public ResponseEntity<BankAccountImpl> createAccount(@Valid  @RequestBody BankAccountImpl bankAccount) {
        return new ResponseEntity<BankAccountImpl>(bankAccountService.createAccount(bankAccount), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<BankAccountImpl>> getAllAccounts() {
        return new ResponseEntity<List<BankAccountImpl>>(bankAccountService.getAllAccounts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BankAccountImpl> getAccountById(@Valid @PathVariable("id") long id) {
        return new ResponseEntity<BankAccountImpl>(bankAccountService.getAccountById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BigDecimal> credit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount) {
        return new ResponseEntity<BigDecimal>(bankAccountService.credit(id, amount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BigDecimal> debit(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "amount") BigDecimal amount) {
        return new ResponseEntity<BigDecimal>(bankAccountService.debit(id, amount), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BigDecimal> updateMaxOverdraft(@Valid @PathVariable("id") long id, @Valid @RequestParam(value = "overdraft") BigDecimal amount) {
        return new ResponseEntity<BigDecimal>(bankAccountService.updateMaxOverdraft(id, amount), HttpStatus.OK);
    }

    @Override
    public void deleteAccountById(@Valid @PathVariable("id") long id) {
        bankAccountService.deleteAccountById(id);
    }

}
