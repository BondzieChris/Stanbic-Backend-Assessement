package com.stanbic.customerapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stanbic.customerapi.model.Customer;
import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.repository.CustomerRepository;

@RestController
@RequestMapping("api/v1/customers")
//@CrossOrigin("http:://localhost")
public class CustomerController {

   @Autowired
   private CustomerRepository modelRepo;

   // get all users
   @GetMapping
   public List<Customer> getAllCustomers() {
       return this.modelRepo.findAll();
   }

   // get user by id
   @GetMapping("/{id}")
   public Customer getCustomerById(@PathVariable (value = "id") long id) {
       return this.modelRepo.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
   }

   // create user
   @PostMapping
   public Customer createCustomer(@Valid @RequestBody Customer user) {
       return this.modelRepo.save(user);
   }

   // update user
   @PutMapping("/{id}")
   public Customer updateCustomer(@Valid @RequestBody Customer user, @PathVariable ("id") long id) {
       
    Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
   //    existingCustomer.setFirstName(user.getFirstName());
        return existingCustomer;
   }

   // delete user by id
   @DeleteMapping("/{id}")
   public ResponseEntity<Customer> deleteCustomer(@PathVariable ("id") long id){
       Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
       this.modelRepo.delete(existingCustomer);
       return ResponseEntity.ok().build();
   }
}