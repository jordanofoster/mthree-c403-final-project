package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface BankCustomerService {

    BankCustomerImpl createCustomer(BankCustomerImpl bankCustomerObj);

    List<BankCustomerImpl> getAllCustomers();

    BankCustomerImpl getCustomerById(long id) throws EntityNotFoundException;

    BankCustomerImpl updateCustomer(BankCustomerImpl updatedBankCustomerObj, long id) throws EntityNotFoundException;

    void deleteCustomerById(long id);

}
