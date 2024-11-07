package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface BankAccountService {

    void createAccount(BankAccount a);
    BankAccount getAccountById(int id);
    ArrayList<BankAccount> getAllAccounts();
    void updateAccount(int accountNumber, BankAccount a);
    void deleteAccount(BankAccount a);

}
