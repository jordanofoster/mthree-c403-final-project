package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dao.BankTransactionRepository;
import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer class implementation for the MVC substack responsible for handling bank transactions.
 */
@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    /**
     * Autowired variable that permits access to the underlying transaction JPA Repository interface.
     */
    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    /**
     * Autowired variable that permits access to the underlying accounts JPA Repository interface.
     * Required because we must check that the involved accounts exist within the database.
     */
    @Autowired
    private BankAccountServiceImpl bankAccountService;

    /**
     * Validates and saves the provided transaction record to the underlying JPA repository.
     * @param bankTransactionObj the BankTransaction object to be validated and saved to the repository.
     * @return BankTransaction object representing the new record as has been saved to the underlying JPA repository.
     * @throws InsufficientAccountBalanceException thrown when the crediting (sending) account lacks the funds/overdraft necessary to complete the transaction.
     * @throws NoResultException thrown when either of the account records involved in the transaction are not present in the JPA repository.
     */
    @Override
    public BankTransactionImpl createTransaction(BankTransactionImpl bankTransactionObj) throws IllegalArgumentException, NoResultException {

        BankAccountImpl sendingAccount = bankAccountService.getAccountById(bankTransactionObj.getSendingAccount().getAccountNumber());
        BankAccountImpl receivingAccount = bankAccountService.getAccountById(bankTransactionObj.getReceivingAccount().getAccountNumber());
        BigDecimal amount = bankTransactionObj.getTransactionAmount().setScale(2, RoundingMode.HALF_UP);

        bankAccountService.credit(sendingAccount.getAccountNumber(), amount);
        bankAccountService.debit(receivingAccount.getAccountNumber(), amount);

        return bankTransactionRepository.saveAndFlush(bankTransactionObj);

    }

    /**
     * Gets all transactions currently in the underlying JPA Repository.
     * @return List of BankTransaction object representing the state of the JPA Repository.
     */
    @Override
    public List<BankTransactionImpl> getAllTransactions() {
        return bankTransactionRepository.findAll();
    }

    /**
     * Gets a transaction by the timestamp provided.
     * @param timestamp the timestamp with which to search for a record.
     * @return BankTransaction object representing the record in the underlying JPA repository.
     * @throws NoResultException thrown when no record with the timestamp provided exists in the underlying JPA repository.
     */
    @Override
    public BankTransactionImpl getTransactionByTimestamp(Timestamp timestamp) throws NoResultException {
        try { return bankTransactionRepository.findById(timestamp).orElseThrow(); } catch (NoSuchElementException e) {
            // Just wrapping this since EntityNotFoundException is /specifically/ expected when dealing with databases.
            throw new NoResultException("No transaction record with this timestamp ID exists.");
        }
    }

    /**
     * Updates the transaction method for a given record based on the timestamp provided.
     * @param timestamp the timestamp with which to search for a record.
     * @param updatedTransactionMethod String representing the new transaction method.
     * @return BankTransaction object representing the updated state of the record in the underlying JPA Repository.
     * @throws NoResultException thrown when no record with the timestamp provided exists in the underlying JPA Repository.
     */
    @Override
    public BankTransactionImpl updateTransactionMethod(Timestamp timestamp, String updatedTransactionMethod) throws NoResultException {
        BankTransactionImpl transactionToUpdate;

        try {
            transactionToUpdate = bankTransactionRepository.findById(timestamp).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new NoResultException("No transaction record with this timestamp ID exists.");
        }

        transactionToUpdate.setTransactionMethod(updatedTransactionMethod);
        return bankTransactionRepository.saveAndFlush(transactionToUpdate);
    }

}
