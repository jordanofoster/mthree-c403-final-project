package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.persistence.NoResultException;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service layer interface for the MVC substack responsible for handling bank transactions.
 */
public interface BankTransactionService {

    /**
     * Validates and saves the provided transaction record to the underlying JPA repository.
     * @param bankTransactionObj the BankTransaction object to be validated and saved to the repository.
     * @return BankTransaction object representing the new record as has been saved to the underlying JPA repository.
     * @throws InsufficientAccountBalanceException thrown when the crediting (sending) account lacks the funds/overdraft necessary to complete the transaction.
     * @throws NoResultException thrown when either of the account records involved in the transaction are not present in the JPA repository.
     */
    BankTransactionImpl createTransaction (BankTransactionImpl bankTransactionObj) throws InsufficientAccountBalanceException, NoResultException;

    /**
     * Gets all transactions currently in the underlying JPA Repository.
     * @return List of BankTransaction object representing the state of the JPA Repository.
     */
    List<BankTransactionImpl> getAllTransactions();

    /**
     * Gets a transaction by the timestamp provided.
     * @param timestamp the timestamp with which to search for a record.
     * @return BankTransaction object representing the record in the underlying JPA repository.
     * @throws NoResultException thrown when no record with the timestamp provided exists in the underlying JPA repository.
     */
    BankTransactionImpl getTransactionByTimestamp(Timestamp timestamp) throws NoResultException;

    /**
     * Updates the transaction method for a given record based on the timestamp provided.
     * @param timestamp the timestamp with which to search for a record.
     * @param updatedTransactionMethod String representing the new transaction method.
     * @return BankTransaction object representing the updated state of the record in the underlying JPA Repository.
     * @throws NoResultException thrown when no record with the timestamp provided exists in the underlying JPA Repository.
     */
    BankTransactionImpl updateTransactionMethod(Timestamp timestamp, String updatedTransactionMethod) throws NoResultException;
}
