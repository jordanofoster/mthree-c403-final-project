package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankAccountStub;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.*;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    @Rollback(value = false)
    public void saveAccountTest() {
        BankAccount account = new BankAccountStub();

        //bankAccountRepository.save(account);
    }
}
