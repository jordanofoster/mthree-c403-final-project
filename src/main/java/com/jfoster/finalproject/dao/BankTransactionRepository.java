package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankTransaction;
import com.jfoster.finalproject.dto.BankTransactionImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, LocalDateTime> {

    List<BankTransaction> findBySendingAccount(BankAccount sending_account);
    List<BankTransaction> findByReceivingAccount(BankAccount receiving_account);
    List<BankTransaction> findByTransactionAmount(BigDecimal transaction_amount);
    List<BankTransaction> findByTransactionMethod(String transaction_method);

}