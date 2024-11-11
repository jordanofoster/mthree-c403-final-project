package com.jfoster.finalproject.service;

import com.jfoster.finalproject.dto.BankCustomerImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Service layer interface for the MVC substack responsible for handling bank customers.
 */
public interface BankCustomerService {

    /**
     * Saves the provided customer record to the underlying JPA repository.
     * @param bankCustomerObj the BankCustomer object to be saved to the repository.
     * @return BankCustomer object representing the new record as has been saved to the underlying JPA repository.
     */
    BankCustomerImpl createCustomer(BankCustomerImpl bankCustomerObj);

    /**
     * Gets all customers currently in the underlying JPA Repository.
     * @return List of BankCustomer objects representing the state of the JPA Repository.
     */
    List<BankCustomerImpl> getAllCustomers();

    /**
     * Gets a customer by the Id provided.
     * @param id The id with which to search for a record.
     * @return BankCustomer object representing the record in the underlying JPA Repository with the same ID.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BankCustomerImpl getCustomerById(long id) throws NoResultException;

    /**
     * Updates a customer record according to the differential between it and the BankCustomer object provided.
     * @param updatedBankCustomerObj BankCustoemr object representing the updated fields for the record.
     * @param id The id of the record to be updated.
     * @return BankCustomer object representing the updated state of the record in the underlying JPA Repository.
     * @throws NoResultException thrown when no record with the ID provided exists in the underlying JPA Repository.
     */
    BankCustomerImpl updateCustomer(BankCustomerImpl updatedBankCustomerObj, long id) throws NoResultException;

    /**
     * Deletes a customer from the JPA Repository based on the ID provided.
     * @param id ID of the customer record to delete.
     */
    void deleteCustomerById(long id);

}
