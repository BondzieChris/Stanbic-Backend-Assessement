package com.stanbic.customerapi.repository;

import java.util.UUID;

import com.stanbic.customerapi.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Remove @Repository below to disable auto REST api:
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>{
//    Account findByUserName(String username);
}