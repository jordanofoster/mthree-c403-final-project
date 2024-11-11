package com.jfoster.finalproject.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountImplTest {

    private BankCustomerImpl testCustomer;
    private BankAccountImpl testAccount;
    private String sortCode,iban,maxOverdraft;

    public BankAccountImplTest() {

    }

    @BeforeEach
    public void beforeEach() {
        testCustomer = new BankCustomerImpl();
        sortCode = "00-00-00";
        iban = "AB00000000000000000000000000000000";
        maxOverdraft = "0.00";

        testAccount = new BankAccountImpl(
                testCustomer,
                sortCode,
                iban,
                maxOverdraft
        );
    }

    @Test
    public void testGetAccountOwner() {
        assertEquals(testAccount.getAccountOwner(), testCustomer);
    }

    @Test
    public void testGetSortCode() {
        assertEquals(testAccount.getSortCode(), sortCode);
    }

    @Test
    public void testGetIban() {
        assertEquals(testAccount.getIban(), iban);
    }

    @Test
    public void testGetBalance() {
        assertEquals(testAccount.getBalance(), new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testGetMaxOverdraft() {
        assertEquals(testAccount.getMaxOverdraft(), new BigDecimal(maxOverdraft).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testSetSortCode() {
        assertEquals(testAccount.getSortCode(), sortCode);
        String newSortCode = "11-11-11";
        testAccount.setSortCode(newSortCode);
        assertEquals(testAccount.getSortCode(), newSortCode);
    }

    @Test
    public void testSetIban() {
        assertEquals(testAccount.getIban(), iban);
        String newIban = "AB00000000000000000000000000000001";
        testAccount.setIban(newIban);
        assertEquals(testAccount.getIban(), newIban);
    }

    @Test
    public void testSetBalance() {
        assertEquals(testAccount.getBalance(), new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP));
        BigDecimal newBalance = new BigDecimal("25.00").setScale(2,RoundingMode.HALF_UP);
        testAccount.setBalance(newBalance);
        assertEquals(testAccount.getBalance(), newBalance);
    }

    @Test
    public void testSetMaxOverdraft() {
        assertEquals(testAccount.getMaxOverdraft(), new BigDecimal(maxOverdraft).setScale(2,RoundingMode.HALF_UP));
        BigDecimal newMaxOverdraft = new BigDecimal("25.00").setScale(2,RoundingMode.HALF_UP);
        testAccount.setMaxOverdraft(newMaxOverdraft);
        assertEquals(testAccount.getMaxOverdraft(), newMaxOverdraft);
    }

}
