package com.stanbic.customerapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stanbic.customerapi.model.Customer;

//Remove @RepositoryRestResource below to disable auto REST api:
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
//    entityName findByUserName(String username);
}