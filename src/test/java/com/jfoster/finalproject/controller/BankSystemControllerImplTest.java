package com.jfoster.finalproject.controller;

import com.jfoster.finalproject.service.BankAccountService;
import com.jfoster.finalproject.service.BankCustomerService;
import com.jfoster.finalproject.service.BankTransactionService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankSystemControllerImplTest {

    BankAccountService bankAccountService;
    BankCustomerService bankCustomerService;
    BankTransactionService bankTransactionService;

    public BankSystemControllerImplTest() {

    }

    @BeforeEach
    void beforeEach() {

    }

    @AfterEach
    void afterEach() {

    }

    // Create

    @Test
    public void testAddCustomer() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testAddAccount() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testAddTransaction() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    // Read

    @Test
    public void testGetCustomers() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testGetCustomerById() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testGetAccounts() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testGetAccountById() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testGetTransactions() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testGetTransactionByTimestamp() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    // Update

    @Test
    public void testUpdateCustomer() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testUpdateAccount() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testUpdateTransaction() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    // Delete

    @Test
    public void testDeleteCustomerById() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testDeleteAccountById() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }

    @Test
    public void testDeleteTransactionByTimestamp() throws Exception {
        throw new UnsupportedOperationException("Test not implemented.");
    }


}
