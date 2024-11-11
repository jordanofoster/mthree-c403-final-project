package com.jfoster.finalproject.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccountStub implements BankAccount {

    @Override
    public Long getAccountNumber() {
        return 1L;
    }

    @Override
    public BankCustomer getAccountOwner() {
        return new BankCustomerStub();
    }

    @Override
    public String getSortCode() {
        return "00-00-00";
    }

    @Override
    public String getIban() {
        return "AB00000000000000000000000000000000";
    }

    @Override
    public BigDecimal getBalance() {
        return new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getMaxOverdraft() {
        return new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void setSortCode(String s) {}

    @Override
    public void setIban(String s) {}

    @Override
    public void setBalance(BigDecimal bd) {}

    @Override
    public void setMaxOverdraft(BigDecimal bd) {}

}
