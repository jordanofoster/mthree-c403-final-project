package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface BankTransaction {
    Timestamp getTransactionTimestamp();
    BankAccountImpl getSendingAccount();
    BankAccountImpl getReceivingAccount();
    BigDecimal getTransactionAmount();
    String getTransactionMethod();
}
