package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankAccountImpl;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;

/**
 * Service layer interface for the MVC substack responsible for handling bank accounts.
 */
public interface BankAccountService {

    /**
     * Saves the provided bank account record to the underlying JPA repository.
     * @param bankAccountObj the BankAccount object to be saved to the repository.
     * @return BankAccount object representing the new record as has been saved to the underlying JPA repository.
     * @throws InvalidParameterException thrown when the parameters provided fail service-layer validation requirements.
     */
    BankAccountImpl createAccount(BankAccountImpl bankAccountObj) throws InvalidParameterException;

    /**
     * Gets all accounts currently in the underlying JPA Repository.
     * @return List of BankAccount objects representing the state of the JPA Repository.
     */
    List<BankAccountImpl> getAllAccounts();

    /**
     * Gets an account by the Id provided.
     * @param id The id with which to search for a record.
     * @return BankAccount object representing the record in the underlying JPA Repository with the same ID.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BankAccountImpl getAccountById(long id) throws NoResultException;

    /**
     * Deposits money into an account within the JPA repository.
     * @param id ID of the account record to be used.
     * @param amount represents the amount of money to deposit. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new balance of the account following the debit.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BigDecimal debit(long id, BigDecimal amount) throws NoResultException;

    /**
     * Withdraws money from an account within the JPA repository.
     * @param id ID of the account record to be used.
     * @param amount represents the amount of money to withdraw. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new balance of the account following the credit.
     * @throws InsufficientAccountBalanceException thrown when the new balance would exceed the maximum allowable overdraft.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BigDecimal credit(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException;

    /**
     * Updates the maximum overdraft allowable on an account within the JPA Repository.
     * @param id ID of the account record to be used.
     * @param amount represents the new maximum overdraft. Subject to scale of 2 and rounding mode of HALF_UP.
     * @return BigDecimal representing the new maximum overdraft of the account, for client-side validation purposes.
     * @throws InsufficientAccountBalanceException thrown when the new overdraft is less than the pre-existing negative overdraft within the account.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BigDecimal updateMaxOverdraft(long id, BigDecimal amount) throws InsufficientAccountBalanceException, NoResultException;

    /**
     * Deletes an account from the JPA Repository based on the ID provided.
     * @param id ID of the account record to delete.
     */
    void deleteAccountById(long id);

}
