package com.jfoster.finalproject.dto;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "transactions")
public class BankTransactionImpl implements BankTransaction {

    @Id
    Timestamp transaction_timestamp;

    @ManyToOne
    @JoinColumn(name="account_number", nullable = false)
    private BankAccount sending_account;

    @ManyToOne
    @JoinColumn(name="account_number", nullable = false)
    private BankAccount receiving_account;

    @Column
    private BigDecimal transaction_amount;

    @Column
    private String transaction_method;

    BankTransactionImpl(Timestamp transactionTime, BankAccount sendingAccount, BankAccount receivingAccount, BigDecimal transactionAmount, String transactionMethod) {
        throw new UnsupportedOperationException("Constructor not implemented.");
    }

    public Timestamp getTransactionTimestamp() {
        throw new UnsupportedOperationException("Method not implemented.");
    };

    public BankAccount getSendingAccount() {
        throw new UnsupportedOperationException("Method not implemented.");
    };

    public BankAccount getReceivingAccount() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public BigDecimal getTransactionAmount() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public String getTransactionMethod() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

}
