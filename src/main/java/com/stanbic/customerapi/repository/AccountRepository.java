package com.stanbic.customerapi.repository;

import java.util.List;

import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Remove @Repository below to disable auto REST api:
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
//    Account findByUserName(String username);
    List<Account> findByCustomer(Customer customer);
    
    Account findByAccountNumber(long acc);

}