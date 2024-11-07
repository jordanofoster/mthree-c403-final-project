package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long> {

    List<BankCustomer> findByName(String name);
    List<BankCustomer> findByAddress(String address);
    List<BankCustomer> findByPhone(String phone);
    List<BankCustomer> findByEmail(String email);

}
