package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankTransaction;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public interface BankTransactionService {

    void createTransaction(BankTransaction t);
    void getTransactionByTimestamp(BankTransaction t);
    ArrayList<BankTransaction> getAllTransactions();
    void updateTransaction(Timestamp timestamp, BankTransaction t);
    void deleteTransaction(BankTransaction t);

}
