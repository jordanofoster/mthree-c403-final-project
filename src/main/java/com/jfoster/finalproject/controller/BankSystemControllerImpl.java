package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankCustomer;
import com.jfoster.finalproject.dto.BankTransaction;
import com.jfoster.finalproject.service.BankAccountService;
import com.jfoster.finalproject.service.BankCustomerService;
import com.jfoster.finalproject.service.BankTransactionService;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BankSystemControllerImpl implements BankSystemController {

    private BankAccountService bankAccountService;
    private BankCustomerService bankCustomerService;
    private BankTransactionService bankTransactionService;
    
    public BankSystemControllerImpl(BankAccountService bankAccountService, BankCustomerService bankCustomerService, BankTransactionService bankTransactionService) {
        this.bankAccountService = bankAccountService;
        this.bankCustomerService = bankCustomerService;
        this.bankTransactionService = bankTransactionService;
    }
    
    @Override
    public BankCustomer addCustomer(BankCustomer bankCustomerObj) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public BankAccount addAccount(BankAccount bankAccountObj) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public BankTransaction addTransaction(BankTransaction bankTransactionObj) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public ResponseEntity<List<BankCustomer>> getCustomers() {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public BankCustomer getCustomerById(int id) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public ResponseEntity<List<BankAccount>> getAccounts() {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public BankAccount getAccountById(int id) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public ResponseEntity<List<BankTransaction>> getTransactions() {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public BankTransaction getTransactionByTimestamp(Timestamp timestamp) {
        throw new UnsupportedOperationException("Method not implemented.");

    }

    @Override
    public void updateCustomer(BankCustomer updatedBankCustomerObj, int id) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void updateAccount(BankAccount updatedBankAccountObj, int id) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void updateTransaction(BankTransaction updatedBankTransactionObj, LocalDateTime timestamp) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void deleteCustomerById(int id) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void deleteAccountById(int id) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public void deleteTransactionByTimestamp(LocalDateTime timestamp) {
        throw new UnsupportedOperationException("Method not implemented.");
    }
}
