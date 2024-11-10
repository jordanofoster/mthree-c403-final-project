package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Timestamp;
import java.util.List;

public interface BankTransactionService {

    BankTransactionImpl createTransaction (BankTransactionImpl bankTransactionObj) throws InsufficientAccountBalanceException, EntityNotFoundException;

    List<BankTransactionImpl> getAllTransactions();

    BankTransactionImpl getTransactionByTimestamp(Timestamp timestamp) throws EntityNotFoundException;

    BankTransactionImpl updateTransactionMethod(Timestamp timestamp, String updatedTransactionMethod) throws EntityNotFoundException;
}
