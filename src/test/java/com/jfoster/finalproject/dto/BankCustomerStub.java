package com.jfoster.finalproject.dto;

public class BankCustomerStub implements BankCustomer {

    @Override
    public Long getCustomerReferenceNumber() {
        return (long) 1;
    }

    @Override
    public String getName() {
        return "John Doe";
    }

    @Override
    public void setName(String s) {}

    @Override
    public String getPhoneNumber() {
        return "+440300703929";
    }

    @Override
    public void setPhoneNumber(String s) {}

    @Override
    public String getEmail() {
        return "test@example.com";
    }

    @Override
    public void setEmail(String s) {}

    @Override
    public String getAddress() {
        return "10 Downing Street, City of Westminster, London, SW1A 2AA";
    }

    @Override
    public void setAddress(String s) {}

}
