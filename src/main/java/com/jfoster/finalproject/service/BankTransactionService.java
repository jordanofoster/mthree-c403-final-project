package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface BankTransactionService {

    void createTransaction(BankTransaction t);
    void getTransactionByTimestamp(BankTransaction t);
    ArrayList<BankTransaction> getAllTransactions();
    void updateTransaction(LocalDateTime timestamp, BankTransaction t);
    void deleteTransaction(BankTransaction t);

}
