package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

/**
 * Interface that represents a bank account within the system.
 * @author Jordan Foster
 * @version 1
 */
public interface BankAccount {
    Long getAccountNumber();
    BankCustomerImpl getAccountOwner();
    String getSortCode();
    String getIban();
    BigDecimal getBalance();
    BigDecimal getMaxOverdraft();

    void setSortCode(String sortCode);
    void setIban(String iban);
    void setBalance(BigDecimal newBalance);
    void setMaxOverdraft(BigDecimal maxOverdraft) throws InvalidParameterException;
}
