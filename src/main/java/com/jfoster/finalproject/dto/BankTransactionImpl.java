package com.jfoster.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;

@Entity(name = "transactions")
public class BankTransactionImpl implements BankTransaction {

    @Id
    @JsonProperty("timestamp")
    @Column(name="transaction_timestamp", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp transactionTimestamp = Timestamp.from(Instant.now());

    @ManyToOne
    @JsonProperty("sending_account")
    @JoinColumn(name="sending_account", nullable = false, updatable = false)
    private BankAccountImpl sendingAccount;

    @ManyToOne
    @JsonProperty("receiving_account")
    @JoinColumn(name="receiving_account", nullable = false, updatable = false)
    private BankAccountImpl receivingAccount;

    @JsonProperty("transaction_amount")
    @Column(name="transaction_amount", nullable = false, updatable = false)
    private BigDecimal transactionAmount;

    @JsonProperty("transaction_method")
    @Column(name="transaction_method", nullable = false)
    private String transactionMethod;

    public BankTransactionImpl(){}

    public BankTransactionImpl(BankAccountImpl sendingAccount, BankAccountImpl receivingAccount, BigDecimal transactionAmount, String transactionMethod) {
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.transactionAmount = transactionAmount.setScale(2, RoundingMode.HALF_UP);
        this.transactionMethod = transactionMethod;
    }

    @Override
    public Timestamp getTransactionTimestamp() {
        return this.transactionTimestamp;
    };

    @Override
    public BankAccountImpl getSendingAccount() {
        return this.sendingAccount;
    };

    @Override
    public BankAccountImpl getReceivingAccount() {
        return this.sendingAccount;
    }

    @Override
    public BigDecimal getTransactionAmount() {
        return this.transactionAmount;
    }

    @Override
    public String getTransactionMethod() {
        return this.transactionMethod;
    }

}
