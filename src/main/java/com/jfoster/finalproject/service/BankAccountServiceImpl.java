package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dao.BankAccountRepository;
import com.jfoster.finalproject.dto.BankAccountImpl;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountImpl createAccount(BankAccountImpl bankAccountObj) throws InvalidParameterException {
        try {
            return bankAccountRepository.saveAndFlush(bankAccountObj);
        } catch (Exception e) {
            throw new InvalidParameterException("Could not save to repository.");
        }
    }

    @Override
    public List<BankAccountImpl> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccountImpl getAccountById(long id) throws NoResultException {
        try { return bankAccountRepository.findById(id).orElseThrow(); } catch (NoSuchElementException e) {
            throw new NoResultException("No account with this ID present in database.");
        }
    }

    @Override
    public BigDecimal debit(long id, BigDecimal amount) throws NoResultException {

        Optional<BankAccountImpl> result = bankAccountRepository.findById(id);
        if (result.isEmpty()) { throw new NoResultException("No account with this ID present in database."); }
        else {
            BankAccountImpl updatedBankAccount = result.get();
            updatedBankAccount.setBalance(updatedBankAccount.getBalance().add(amount));
            updatedBankAccount = bankAccountRepository.saveAndFlush(updatedBankAccount);
            return updatedBankAccount.getBalance();
        }

    }

    @Override
    public BigDecimal credit(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException {

        Optional<BankAccountImpl> result = bankAccountRepository.findById(id);
        if (result.isEmpty()) { throw new NoResultException("No account with this ID present in database."); }
        else {
            BankAccountImpl updatedBankAccount = result.get();
            BigDecimal updatedBalance = updatedBankAccount.getBalance().subtract(amount).setScale(2, RoundingMode.HALF_UP);
            if (updatedBalance.compareTo(new BigDecimal("0.0").subtract(updatedBankAccount.getMaxOverdraft())) < 0) {
                throw new InsufficientAccountBalanceException("Proposed credit would exceed maximum overdraft.");
            } else {
                updatedBankAccount.setBalance(updatedBalance);
                updatedBankAccount = bankAccountRepository.saveAndFlush(updatedBankAccount);
                return updatedBankAccount.getBalance();
            }
        }
    }

    @Override
    public BigDecimal updateMaxOverdraft(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException {

        Optional<BankAccountImpl> result = bankAccountRepository.findById(id);
        if (result.isEmpty()) { throw new NoResultException("No account with this ID present in database."); }
        else {
            BankAccountImpl updatedBankAccount = result.get();
            if (updatedBankAccount.getBalance().compareTo(new BigDecimal("0.0").subtract(amount).setScale(2, RoundingMode.HALF_UP)) < 0) {
                throw new InsufficientAccountBalanceException("Proposed change to overdraft limit is below the current amount the account is overdrawn.");
            } else {
                updatedBankAccount.setMaxOverdraft(amount);
                updatedBankAccount = bankAccountRepository.saveAndFlush(updatedBankAccount);
                return updatedBankAccount.getMaxOverdraft();
            }
        }
    }

    @Override
    public void deleteAccountById(long id) {
        bankAccountRepository.deleteById(id);
    }

}
