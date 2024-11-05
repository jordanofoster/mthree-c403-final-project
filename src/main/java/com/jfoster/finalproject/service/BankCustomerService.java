package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankCustomer;

public interface BankCustomerService {
    void createCustomer(BankCustomer c);
    BankCustomer getCustomerById(long customerId);
    void updateCustomer(long customerId, BankCustomer c);
    void deleteCustomer(BankCustomer c);
}
