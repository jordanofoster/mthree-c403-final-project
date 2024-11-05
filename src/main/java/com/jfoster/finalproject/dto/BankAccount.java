package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccount {
    BigDecimal getBalance();
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    String getAccountNumber();
    String getSortCode();
    String getIBAN();
    void addTransaction(BankTransaction t);
    List<BankTransaction> getTransactions();
    BankTransaction getTransactionById();
}
