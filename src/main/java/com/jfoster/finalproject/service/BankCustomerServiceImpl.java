package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dao.BankCustomerRepository;
import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service layer class implementation for the MVC substack responsible for handling bank customers.
 */
@Service
public class BankCustomerServiceImpl implements BankCustomerService {

    /**
     * Autowired variable that permits access to the underlying JPA Repository interface.
     */
    @Autowired
    private BankCustomerRepository bankCustomerRepository;

    /**
     * Saves the provided customer record to the underlying JPA repository.
     * @param bankCustomerObj the BankCustomer object to be saved to the repository.
     * @return BankCustomer object representing the new record as has been saved to the underlying JPA repository.
     */
    @Override
    public BankCustomerImpl createCustomer(BankCustomerImpl bankCustomerObj) {
        return bankCustomerRepository.saveAndFlush(bankCustomerObj);
    }

    /**
     * Gets all customers currently in the underlying JPA Repository.
     * @return List of BankCustomer objects representing the state of the JPA Repository.
     */
    @Override
    public List<BankCustomerImpl> getAllCustomers() {
        return bankCustomerRepository.findAll();
    }

    /**
     * Gets a customer by the Id provided.
     * @param id The id with which to search for a record.
     * @return BankCustomer object representing the record in the underlying JPA Repository with the same ID.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    @Override
    public BankCustomerImpl getCustomerById(long id) throws NoResultException {
        try { return bankCustomerRepository.findById(id).orElseThrow(); }
        catch (NoSuchElementException e) { throw new NoResultException("No customer with this ID present in database."); }
    }

    /**
     * Updates a customer record according to the differential between it and the BankCustomer object provided.
     * @param updatedBankCustomerObj BankCustoemr object representing the updated fields for the record.
     * @param id The id of the record to be updated.
     * @return BankCustomer object representing the updated state of the record in the underlying JPA Repository.
     * @throws EntityNotFoundException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    @Override
    public BankCustomerImpl updateCustomer(BankCustomerImpl updatedBankCustomerObj, long id) throws NoResultException {
        Optional<BankCustomerImpl> result = bankCustomerRepository.findById(id);
        if (result.isPresent()) {
            BankCustomerImpl customerToUpdate = result.get();
            customerToUpdate.setAddress(updatedBankCustomerObj.getAddress());
            customerToUpdate.setName(updatedBankCustomerObj.getName());
            customerToUpdate.setEmail(updatedBankCustomerObj.getEmail());
            customerToUpdate.setPhoneNumber(updatedBankCustomerObj.getPhoneNumber());

            return bankCustomerRepository.saveAndFlush(customerToUpdate);
        } else {
            throw new NoResultException("No customer with this ID present in database.");
        }
    }

    /**
     * Deletes a customer from the JPA Repository based on the ID provided.
     * @param id ID of the customer record to delete.
     */
    @Override
    public void deleteCustomerById(long id) {
        bankCustomerRepository.deleteById(id);
    }

}
