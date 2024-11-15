package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankTransaction;
import com.jfoster.finalproject.dto.BankTransactionImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * JPA repository interface for the BankTransaction substack of the MVC System.
 * Method definitions follow expected format for auto-generation and so they do not
 * have javadoc definitions.
 */
@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransactionImpl, Timestamp> {

    List<BankTransaction> findBySendingAccount(BankAccount sending_account);
    List<BankTransaction> findByReceivingAccount(BankAccount receiving_account);
    List<BankTransaction> findByTransactionAmount(BigDecimal transaction_amount);
    List<BankTransaction> findByTransactionMethod(String transaction_method);

}