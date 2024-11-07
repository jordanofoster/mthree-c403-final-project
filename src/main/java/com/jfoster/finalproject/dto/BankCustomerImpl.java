package com.jfoster.finalproject.dto;

import jakarta.persistence.*;

import java.util.Set;

@Entity(name="customers")
public class BankCustomerImpl {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int customer_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    public BankCustomerImpl(String name, String address, String phone, String email) {
        throw new UnsupportedOperationException("Constructor not implemented.");
    }

    public String getCRN() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public String getName() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public void setName() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public String getPhoneNumber() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public void setPhoneNumber() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public String getEmail() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public void setEmail() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public Set<BankAccount> getAccounts() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public BankAccount getAccountByNumber(String accountNumber) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public void createAccount() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    public void deleteAccountByNumber() {
        throw new UnsupportedOperationException("Method not implemented.");
    }

}
