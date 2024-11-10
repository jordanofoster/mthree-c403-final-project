package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dao.BankTransactionRepository;
import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.dto.BankTransactionImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private BankAccountServiceImpl bankAccountService;

    @Override
    public BankTransactionImpl createTransaction(BankTransactionImpl bankTransactionObj) throws IllegalArgumentException, EntityNotFoundException {

        BankAccountImpl sendingAccount = bankAccountService.getAccountById(bankTransactionObj.getSendingAccount().getAccountNumber());
        BankAccountImpl receivingAccount = bankAccountService.getAccountById(bankTransactionObj.getReceivingAccount().getAccountNumber());
        BigDecimal amount = bankTransactionObj.getTransactionAmount().setScale(2, RoundingMode.HALF_UP);

        bankAccountService.credit(sendingAccount.getAccountNumber(), amount);
        bankAccountService.debit(receivingAccount.getAccountNumber(), amount);

        return bankTransactionRepository.saveAndFlush(bankTransactionObj);

    }

    @Override
    public List<BankTransactionImpl> getAllTransactions() {
        return bankTransactionRepository.findAll();
    }

    @Override
    public BankTransactionImpl getTransactionByTimestamp(Timestamp timestamp) throws EntityNotFoundException {
        try { return bankTransactionRepository.findById(timestamp).orElseThrow(); } catch (NoSuchElementException e) {
            // Just wrapping this since EntityNotFoundException is /specifically/ expected when dealing with databases.
            throw new EntityNotFoundException("No transaction record with this timestamp ID exists.");
        }
    }

    @Override
    public BankTransactionImpl updateTransactionMethod(Timestamp timestamp, String updatedTransactionMethod) throws EntityNotFoundException {
        BankTransactionImpl transactionToUpdate;

        try {
            transactionToUpdate = bankTransactionRepository.findById(timestamp).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No transaction record with this timestamp ID exists.");
        }

        transactionToUpdate.setTransactionMethod(updatedTransactionMethod);
        return bankTransactionRepository.saveAndFlush(transactionToUpdate);
    }

}
