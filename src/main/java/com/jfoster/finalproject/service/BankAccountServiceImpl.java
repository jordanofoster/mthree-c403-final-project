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

/**
 * Service layer class implementation for the MVC substack responsible for handling bank accounts.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    /**
     * Autowired variable that permits access to the underlying JPA Repository interface.
     */
    @Autowired
    private BankAccountRepository bankAccountRepository;

    /**
     * Saves the provided bank account record to the underlying JPA repository.
     * @param bankAccountObj the BankAccount object to be saved to the repository.
     * @return BankAccount object representing the new record as has been saved to the underlying JPA repository.
     * @throws InvalidParameterException thrown when the parameters provided fail service-layer validation requirements.
     */
    @Override
    public BankAccountImpl createAccount(BankAccountImpl bankAccountObj) throws InvalidParameterException {
        try {
            return bankAccountRepository.saveAndFlush(bankAccountObj);
        } catch (Exception e) {
            throw new InvalidParameterException("Could not save to repository.");
        }
    }

    /**
     * Gets all accounts currently in the underlying JPA Repository.
     * @return List of BankAccount objects representing the state of the JPA Repository.
     */
    @Override
    public List<BankAccountImpl> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    /**
     * Gets an account by the Id provided.
     * @param id The id with which to search for a record.
     * @return BankAccount object representing the record in the underlying JPA Repository with the same ID.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    @Override
    public BankAccountImpl getAccountById(long id) throws NoResultException {
        try { return bankAccountRepository.findById(id).orElseThrow(); } catch (NoSuchElementException e) {
            throw new NoResultException("No account with this ID present in database.");
        }
    }

    /**
     * Deposits money into an account within the JPA repository.
     * @param id ID of the account record to be used.
     * @param amount represents the amount of money to deposit. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new balance of the account following the debit.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
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

    /**
     * Withdraws money from an account within the JPA repository.
     * @param id ID of the account record to be used.
     * @param amount represents the amount of money to withdraw. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new balance of the account following the credit.
     * @throws InsufficientAccountBalanceException thrown when the new balance would exceed the maximum allowable overdraft.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
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

    /**
     * Updates the maximum overdraft allowable on an account within the JPA Repository.
     * @param id ID of the account record to be used.
     * @param amount represents the new maximum overdraft. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new maximum overdraft of the account, for client-side validation purposes.
     * @throws InsufficientAccountBalanceException thrown when the new overdraft is less than the pre-existing negative overdraft within the account.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
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

    /**
     * Deletes an account from the JPA Repository based on the ID provided.
     * @param id ID of the account record to delete.
     */
    @Override
    public void deleteAccountById(long id) {
        bankAccountRepository.deleteById(id);
    }

}
