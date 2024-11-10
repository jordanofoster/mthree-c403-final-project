package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dao.BankCustomerRepository;
import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BankCustomerServiceImpl implements BankCustomerService {

    @Autowired
    private BankCustomerRepository bankCustomerRepository;

    @Override
    public BankCustomerImpl createCustomer(BankCustomerImpl bankCustomer) {
        return bankCustomerRepository.saveAndFlush(bankCustomer);
    }

    @Override
    public List<BankCustomerImpl> getAllCustomers() {
        return bankCustomerRepository.findAll();
    }

    @Override
    public BankCustomerImpl getCustomerById(long id) throws EntityNotFoundException {
        try { return bankCustomerRepository.findById(id).orElseThrow(); }
        catch (NoSuchElementException e) { throw new EntityNotFoundException("No customer with this ID present in database."); }
    }

    @Override
    public BankCustomerImpl updateCustomer(BankCustomerImpl updatedBankCustomerObj, long id) throws EntityNotFoundException {
        Optional<BankCustomerImpl> result = bankCustomerRepository.findById(id);
        if (result.isPresent()) {
            BankCustomerImpl customerToUpdate = result.get();
            customerToUpdate.setAddress(updatedBankCustomerObj.getAddress());
            customerToUpdate.setName(updatedBankCustomerObj.getName());
            customerToUpdate.setEmail(updatedBankCustomerObj.getEmail());
            customerToUpdate.setPhoneNumber(updatedBankCustomerObj.getPhoneNumber());

            return bankCustomerRepository.saveAndFlush(customerToUpdate);
        } else {
            throw new EntityNotFoundException("No customer with this ID present in database.");
        }
    }

    @Override
    public void deleteCustomerById(long id) {
        bankCustomerRepository.deleteById(id);
    }

}
