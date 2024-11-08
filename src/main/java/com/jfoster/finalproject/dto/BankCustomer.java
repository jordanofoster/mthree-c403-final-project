package com.jfoster.finalproject.dto;

/**
 * Interface that defines a customer of the bank.
 * @author Jordan Foster
 * @version 1
 */
public interface BankCustomer {
    Long getCustomerReferenceNumber();
    String getName();
    void setName(String name);
    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);
    String getEmail();
    void setEmail(String email);
    String getAddress();
    void setAddress(String address);
}
