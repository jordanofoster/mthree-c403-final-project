package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BankTransaction {
    LocalDateTime getTransactionTime();
    BankAccount getSendingAccount();
    BankAccount getReceivingAccount();
    BigDecimal getTransactionAmount();
    String getTransactionMethod();
}
