package com.jfoster.finalproject.controller;


import com.jfoster.finalproject.dto.BankAccount;
import com.jfoster.finalproject.dto.BankAccountImpl;
import com.jfoster.finalproject.dto.BankAccountStub;
import com.jfoster.finalproject.service.BankAccountServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BankAccountControllerImpl.class)
public class BankAccountControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountServiceImpl bankAccountService;

    @Test
    public void testCreateAccount() throws Exception {
        BankAccountImpl account = new BankAccountImpl();

        given(bankAccountService.createAccount(account)).willReturn(account);

        //mvc.peform.post("/")
    }

    @Test
    void testGetAllAccounts() throws Exception {

    }

    @Test
    public void testGetAccountById() throws Exception {


    }

    @Test
    public void testCredit() throws Exception {

    }

    @Test
    public void testDebit() throws Exception {

    }

    @Test
    public void testMaxOverdraft() throws Exception {

    }

    @Test
    public void testDeleteAccountById() throws Exception {

    }

}
