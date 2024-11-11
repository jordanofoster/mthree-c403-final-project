package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

/**
 * DTO Interface that represents a bank account record within the accounts table.
 */
public interface BankAccount {

    /**
     * Gets the account number of the entity.
     * @return Long object representing the account number (represented within accounts as BIGINT).
     */
    Long getAccountNumber();

    /**
     * Gets the owner of the account.
     * @return BankCustomer object representing the owner of the account (represented within accounts as BankCustomer's primary key).
     */
    BankCustomer getAccountOwner();

    /**
     * Gets the sort code of the account.
     * @return String object representing the sort code of the account (represented within accounts as CHAR(8))..
     */
    String getSortCode();

    /**
     * Gets the IBAN of the account
     * @return String object representing the IBAN of the account (represented within accounts as VARCHAR(34).
     */
    String getIban();

    /**
     * Gets the balance of the account
     * @return BigDecimal(2, HALF_UP) object representing the account balance (represented within accounts as DECIMAL(19,2).
     */
    BigDecimal getBalance();

    /**
     * Gets the maximum overdraft of the account
     * @return BigDecimal(2, HALF_UP) object representing the maximum overdraft (represented within accounts as DECIMAL(19,2).
     */
    BigDecimal getMaxOverdraft();

    /**
     * Sets the sort code of the account.
     * @param sortCode String to set the entity variable to.
     */
    void setSortCode(String sortCode);

    /**
     * Sets the IBAN of the account
     * @param iban String to set the entity variable to.
     */
    void setIban(String iban);

    /**
     * sets the balance of the account.
     * Notably, this should only be used by the service layer after validation checks on debit/credit operations pass.
     * @param newBalance BigDecimal object representing the new balance. This will be subject to .setScale(2,HALF_UP).
     */
    void setBalance(BigDecimal newBalance);

    /**
     * Sets the max overdraft of the account.
     * @param maxOverdraft BigDecimal object representing the new overdraft. This will be subject to .setScale(2,HALF_UP).
     */
    void setMaxOverdraft(BigDecimal maxOverdraft);
}
