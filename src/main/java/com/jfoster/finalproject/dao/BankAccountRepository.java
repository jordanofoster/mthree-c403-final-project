package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    void createAccount(BankAccount a);
    BankAccount getAccountById(int id);
    ArrayList<BankAccount> getAllAccounts();
    void updateAccount(int accountNumber, BankAccount a);
    void deleteAccount(BankAccount a);

}
