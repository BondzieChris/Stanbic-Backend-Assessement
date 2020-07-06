package com.stanbic.customerapi.controller;

import java.util.List;

import javax.validation.Valid;

import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.model.Customer;
import com.stanbic.customerapi.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/customers")
public class CustomerController {

   @Autowired
   private CustomerRepository modelRepo;

   // 3. 	Retrieve all customers (5 points)
   @GetMapping
   public List<Customer> getAllCustomers() {
       return this.modelRepo.findAll();
   }

   
   // 4. 	Retrieve a customer’s info by Email or phone number (3 points)
   @GetMapping("/{value}")
   public Customer getCustomerEmail(@PathVariable(name = "value") String value) {

       Customer customer = this.modelRepo.findByEmail(value);

       if(customer == null){
            customer = this.modelRepo.findByPhoneNumber(value);
       }

       return customer;
   }


   // 1. 	Add a new customer (2 points)
   @PostMapping
   public Customer createCustomer(@Valid @RequestBody Customer customer) {
       return this.modelRepo.save(customer);
   }


   // 5. 	Update Customer info (2 points)
   @PutMapping("/{id}")
   public Customer updateCustomer(@Valid @RequestBody Customer customer, @PathVariable("id") long id) {
       
    Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
            existingCustomer.setName(customer.getName());
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            return this.modelRepo.save(existingCustomer);
   }


   // 9. 	Delete a customer and all linked accounts. (3 points)
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id){

       Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
           
       this.modelRepo.delete(existingCustomer);
       return new ResponseEntity<>("All informaton of Customer deleted succesfully", HttpStatus.OK);
     }
 

   
//    7. 	Retrieve all accounts of a customer using email or phone number. (5 points)
   @GetMapping(value = "/{value}/accounts")
   public List<Account> getCustomerAccounts(@PathVariable(name = "value") String value) {

    Customer customer = this.modelRepo.findByEmail(value);

    if(customer == null)
         customer = this.modelRepo.findByPhoneNumber(value);

    return customer.getAccounts();

   }

}