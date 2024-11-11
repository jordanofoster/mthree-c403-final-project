package com.jfoster.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * DTO class implementation for the MVC substack responsible for handling bank transactions.
 */
@Entity(name = "transactions")
public class BankTransactionImpl implements BankTransaction {


    /**
     * Primary key. Represents the timestamp of the transaction and is populated on record creation.
     */
    @Id
    @JsonProperty("timestamp")
    @Column(name="transaction_timestamp", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp transactionTimestamp = Timestamp.from(Instant.now());

    /**
     * Represents the accounts record responsible for financing the transaction via credit.
     * May not be null, and cannot be updated after creation.
     */
    @ManyToOne
    @JsonProperty("sending_account")
    @NotNull
    @JoinColumn(name="sending_account", nullable = false, updatable = false)
    private BankAccountImpl sendingAccount;

    /**
     * Represents the accounts record responsible for receiving the transaction via debit.
     * May not be null, and cannot be updated after creation.
     */
    @ManyToOne
    @JsonProperty("receiving_account")
    @NotNull
    @JoinColumn(name="receiving_account", nullable = false, updatable = false)
    private BankAccountImpl receivingAccount;

    /**
     * Represents the amount to be exchanged between each account.
     * May not contain more than 19 integer digits and 2 fractional digits.
     * May not be null.
     */
    @JsonProperty("transaction_amount")
    @Digits(integer=19,fraction=2)
    @NotNull
    @Column(name="transaction_amount", nullable = false, updatable = false)
    private BigDecimal transactionAmount;

    /**
     * Represents the transaction method used.
     * May not be longer than 45 characters, in line with schema requirements.
     * May not be null.
     */
    @JsonProperty("transaction_method")
    @NotNull
    @Size(max=45)
    @Column(name="transaction_method", nullable = false)
    private String transactionMethod;

    public BankTransactionImpl(){}

    public BankTransactionImpl(BankAccountImpl sendingAccount, BankAccountImpl receivingAccount, BigDecimal transactionAmount, String transactionMethod) {
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.transactionAmount = transactionAmount.setScale(2, RoundingMode.HALF_UP);
        this.transactionMethod = transactionMethod;
    }

    /**
     * Gets the id of the transaction, which is equivalent to the time of creation.
     * @return SQL.Timestamp object representing the creation timestamp of the record.
     */
    @Override
    public Timestamp getTransactionTimestamp() {
        return this.transactionTimestamp;
    }

    /**
     * Gets the BankAccount object that will be credited for the transaction.
     * @return BankAccount object representing the creditor.
     */
    @Override
    public BankAccountImpl getSendingAccount() {
        return this.sendingAccount;
    }

    /**
     * Gets the BankAccount object that will be debited for the transaction.
     * @return BankAccount object representing the debitor.
     */
    @Override
    public BankAccountImpl getReceivingAccount() {
        return this.receivingAccount;
    }

    /**
     * Gets the amount of money to be transferred between both accounts.
     * @return BigDecimal object representing the amount exchanged. Set to a scale of 2 and a rounding mode of HALF_UP.
     */
    @Override
    public BigDecimal getTransactionAmount() {
        return this.transactionAmount;
    }

    /**
     * Gets the transaction method used.
     * @return String object representing the method that was used for this transaction.
     */
    @Override
    public String getTransactionMethod() {
        return this.transactionMethod;
    }

    /**
     * Sets a new transaction method for this transaction.
     * @param transactionMethod String object representing the new transaction method.
     */
    @Override
    public void setTransactionMethod(String transactionMethod) {
        this.transactionMethod = transactionMethod;
    }

}
