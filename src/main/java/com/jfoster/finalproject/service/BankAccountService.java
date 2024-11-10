package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankAccountImpl;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;

public interface BankAccountService {

    BankAccountImpl createAccount(BankAccountImpl bankAccountObj) throws InvalidParameterException;

    List<BankAccountImpl> getAllAccounts();

    BankAccountImpl getAccountById(long id) throws NoResultException;

    BigDecimal debit(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException;

    BigDecimal credit(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException;

    BigDecimal updateMaxOverdraft(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException;

    void deleteAccountById(long id) throws NoResultException;

}
