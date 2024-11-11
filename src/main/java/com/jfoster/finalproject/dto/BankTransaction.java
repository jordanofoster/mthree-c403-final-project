package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * DTO interface for the MVC substack responsible for handling bank transactions.
 */
public interface BankTransaction {

    /**
     * Gets the id of the transaction, which is equivalent to the time of creation.
     * @return SQL.Timestamp object representing the creation timestamp of the record.
     */
    Timestamp getTransactionTimestamp();

    /**
     * Gets the BankAccount object that will be credited for the transaction.
     * @return BankAccount object representing the creditor.
     */
    BankAccount getSendingAccount();

    /**
     * Gets the BankAccount object that will be debited for the transaction.
     * @return BankAccount object representing the debitor.
     */
    BankAccount getReceivingAccount();

    /**
     * Gets the amount of money to be transferred between both accounts.
     * @return BigDecimal object representing the amount exchanged. Set to a scale of 2 and a rounding mode of HALF_UP.
     */
    BigDecimal getTransactionAmount();

    /**
     * Gets the transaction method used.
     * @return String object representing the method that was used for this transaction.
     */
    String getTransactionMethod();

    /**
     * Sets a new transaction method for this transaction.
     * @param transactionMethod String object representing the new transaction method.
     */
    void setTransactionMethod(String transactionMethod);

    /*
     * We only allow the transaction method to be changed because other changes would require any subsequent transactions
     * to be 'rolled back' and recalculated.
     * This is very expensive and could have heavy side effects on the database; I'm not sure what the official bank approach
     * to this is, but I imagine the combination of pending transactions and fraud reporting departments indicates that it isn't
     * a totally solved problem there, either.
     */
}
