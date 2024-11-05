package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, LocalDateTime> {

    void createTransaction(BankTransaction t);
    void getTransactionByTimestamp(BankTransaction t);
    ArrayList<BankTransaction> getAllTransactions();
    void updateTransaction(LocalDateTime timestamp, BankTransaction t);
    void deleteTransaction(BankTransaction t);

}