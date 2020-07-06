package com.stanbic.customerapi.repository;


import com.stanbic.customerapi.model.Customer;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Remove @RepositoryRestResource below to disable auto REST api:
@Repository
@Scope(value = "prototype")
public interface CustomerRepository extends JpaRepository<Customer, Long>{

   Customer findByEmail(String email);
   
   Customer findByPhoneNumber(String tel);

}