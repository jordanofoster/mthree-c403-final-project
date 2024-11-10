package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface BankTransaction {
    Timestamp getTransactionTimestamp();
    BankAccountImpl getSendingAccount();
    BankAccountImpl getReceivingAccount();
    BigDecimal getTransactionAmount();
    String getTransactionMethod();

    /*
     * We only allow the transaction method to be changed because other changes would require any subsequent transactions
     * to be 'rolled back' and recalculated.
     * This is very expensive and could have heavy side effects on the database; I'm not sure what the official bank approach
     * to this is, but I imagine the combination of pending transactions and fraud reporting departments indicates that it isn't
     * a totally solved problem there, either.
     */
    void setTransactionMethod(String transactionMethod);
}
