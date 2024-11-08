package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.dto.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountImpl, Long> {
    List<BankAccount> findByAccountOwner(BankCustomer accountOwner);
    List<BankAccount> findBySortCode(String sortCode);
    List<BankAccount> findByIban(String iban);
}
