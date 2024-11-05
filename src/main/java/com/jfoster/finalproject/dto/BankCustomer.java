package com.jfoster.finalproject.dto;

import java.util.Set;

public interface BankCustomer {
    String getCRN();
    String getName();
    void setName();
    String getPhoneNumber();
    void setPhoneNumber();
    String getEmail();
    void setEmail();
    Set<BankAccount> getAccounts();
    BankAccount getAccountByNumber(String accountNumber);
    void createAccount();
    void deleteAccountByNumber();
}
