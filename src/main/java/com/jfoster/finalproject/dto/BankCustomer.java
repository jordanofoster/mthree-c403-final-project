package com.jfoster.finalproject.dto;

/**
 * DTO Interface that represents a bank customer record within the customers table.
 */
public interface BankCustomer {

    /**
     * Gets the customer reference number of the entity.
     * @return Long object representing the customer reference number (represented within customers as BIGINT).
     */
    Long getCustomerReferenceNumber();

    /**
     * Gets the full name of the customer.
     * @return String object representing the customer's full name (represented within customers as VARCHAR(45)).
     */
    String getName();

    /**
     * Sets the full name of the customer.
     * @param name String to set the entity variable to.
     */
    void setName(String name);

    /**
     * Gets the phone number of the customer.
     * @return String object representing the customer's phone number (represented within customers as VARCHAR(25)).
     */
    String getPhoneNumber();

    /**
     * Sets the phone number of the customer.
     * @param phoneNumber String to set the entity variable to.
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * Gets the e-mail address of the customer.
     * @return String object representing the customer's e-mail address (represented within customers as VARCHAR(254)).
     */
    String getEmail();

    /**
     * Sets the e-mail address of the customer.
     * @param email String to set the entity variable to.
     */
    void setEmail(String email);

    /**
     * Gets the residential address of the customer.
     * @return String object representing the customer's address (represented within customers as VARCHAR(45)).
     */
    String getAddress();

    /**
     * Sets the residential address of the customer.
     * @param address String to set the entity variable to.
     */
    void setAddress(String address);
}
