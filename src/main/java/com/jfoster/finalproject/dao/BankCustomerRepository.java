package com.jfoster.finalproject.dao;

import com.jfoster.finalproject.dto.BankCustomer;
import com.jfoster.finalproject.dto.BankCustomerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JPA repository interface for the BankCustomer substack of the MVC System.
 * Method definitions follow expected format for auto-generation and so they do not
 * have javadoc definitions.
 */
@Repository
public interface BankCustomerRepository extends JpaRepository<BankCustomerImpl, Long> {

    List<BankCustomer> findByName(String name);
    List<BankCustomer> findByAddress(String address);
    List<BankCustomer> findByPhoneNumber(String phone);
    List<BankCustomer> findByEmail(String email);

}
