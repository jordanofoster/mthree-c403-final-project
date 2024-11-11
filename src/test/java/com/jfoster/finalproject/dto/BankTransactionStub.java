package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;

public class BankTransactionStub implements BankTransaction {

    @Override
    public Timestamp getTransactionTimestamp() {
        return Timestamp.from(Instant.now());
    }

    @Override
    public BankAccount getSendingAccount() {
        return new BankAccountStub();
    }

    @Override
    public BankAccount getReceivingAccount() {
        return new BankAccountStub();
    }

    @Override
    public BigDecimal getTransactionAmount() {
        return new BigDecimal("0.0").setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getTransactionMethod() {
        return "Test Method";
    }

    @Override
    public void setTransactionMethod(String s) {}

}
