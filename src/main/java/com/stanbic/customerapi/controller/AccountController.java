package com.stanbic.customerapi.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.model.Customer;
import com.stanbic.customerapi.repository.AccountRepository;
import com.stanbic.customerapi.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "api/v1/accounts")
@Api(value = "Account API", description = "Handles accounts operations")
public class AccountController  {

   @Autowired
   private AccountRepository modelRepo;


   @Autowired
   private CustomerRepository customerRepo;


   //  6. 	Add a new account to an existing customer (3 points)
   @PostMapping("/customer/{id}")
   @ApiOperation(value = "Add a new account to an existing customer")
   public Account addAccountToCustomer(@ApiParam(value = "Account object") @Valid @RequestBody Account account, @ApiParam(value = "ID of Customer") @PathVariable (value = "id") long id) {
            Date date = new Date();
            long accnumber = date.getTime();
            Customer customer =  this.customerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id:"+ id +" cannot be found" ));
            account.setAccountNumber(accnumber);
            account.setCustomer(customer);

         return this.modelRepo.save(account);

   }
   

   // 8. 	Delete an account by account number (2 points)
   @DeleteMapping(value = "/{acc}")
   @ApiOperation(value = "Delete an account by account number")
   public Map<String,String> deleteAccountWithNumber(@ApiParam(value = "account number") @PathVariable ("acc") long acc){

       Account existingAccount = this.modelRepo.findByAccountNumber(acc);

       this.modelRepo.delete(existingAccount);

       Map<String,String> response = new HashMap<String, String>();
       response.put("done", "Account Has Been Deleted");

       return response;
   }






}