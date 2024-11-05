package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomer, Long> {

    void createCustomer(BankCustomer c);
    BankCustomer getCustomerById(long customerId);
    ArrayList<BankCustomer> getAllCustomers();
    void updateCustomer(long customerId, BankCustomer c);
    void deleteCustomer(BankCustomer c);

}
