package com.jfoster.finalproject.dto;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BankTransactionImplTest {

    BankAccountImpl testSendingAccount,testReceivingAccount;
    BankTransactionImpl testTransaction;
    BigDecimal testTransactionAmount;
    String testTransactionMethod;

    public BankTransactionImplTest() {

    }

    @BeforeEach
    public void beforeEach() {
        testSendingAccount = new BankAccountImpl();
        testSendingAccount.setSortCode("00-00-00");

        testReceivingAccount = new BankAccountImpl();
        testReceivingAccount.setSortCode("01-00-00");

        testTransactionAmount = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
        testTransactionMethod = "Test Method";

        testTransaction = new BankTransactionImpl(
                testSendingAccount,
                testReceivingAccount,
                testTransactionAmount,
                testTransactionMethod
        );
    }

    @Test
    public void testGetSendingAccount() {
        assertEquals(testTransaction.getSendingAccount(), testSendingAccount);
    }

    @Test
    public void testGetReceivingAccount() {
        assertEquals(testTransaction.getReceivingAccount(), testReceivingAccount);
    }

    @Test
    public void testGetTransactionAmount() {
        assertEquals(testTransaction.getTransactionAmount(), testTransactionAmount);
    }

    @Test
    public void testGetTransactionMethod() {
        assertEquals(testTransaction.getTransactionMethod(), testTransactionMethod);
    }

    @Test
    public void testSetTransactionMethod() {
        String newTransactionMethod = "New Test Method";

        testTransaction.setTransactionMethod(newTransactionMethod);
        assertEquals(testTransaction.getTransactionMethod(), newTransactionMethod);
    }
}
