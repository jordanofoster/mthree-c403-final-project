package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankAccount;

import java.util.ArrayList;

public interface BankAccountService {

    void createAccount(BankAccount a);
    BankAccount getAccountById(int id);
    ArrayList<BankAccount> getAllAccounts();
    void updateAccount(int accountNumber, BankAccount a);
    void deleteAccount(BankAccount a);

}
