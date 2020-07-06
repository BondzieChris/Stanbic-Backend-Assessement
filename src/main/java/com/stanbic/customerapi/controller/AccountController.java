package com.stanbic.customerapi.controller;
import java.util.Date;

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
public class AccountController {

   @Autowired
   private AccountRepository modelRepo;

  

   @Autowired
   private CustomerRepository customerRepo;

//    get all accounts
   // @GetMapping
   // public List<Account> getAllAccounts() {
        
   //     return this.modelRepo.findAll();
   // }


   

   // get account by id
   // @GetMapping(value = "/{id}")
   // public Account getAccountById(@PathVariable(name = "id") long id) {

   //     return this.modelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));

   // }

   // create account
//    @PostMapping
//    public Account createAccount(@Valid @RequestBody Account account) {

//         Date date = new Date();
//         long accnumber = date.getTime();
//         account.setAccountNumber(accnumber); 
//         return this.modelRepo.save(account);
//    }


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
   

   // update account
//    @PutMapping(value = "/{id}")
//    public Account updateAccount(@Valid @RequestBody Account account, @PathVariable (name = "id") long id) {
//        Account existingAccount = this.modelRepo.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));

//     existingAccount.setCurrentBalance(account.getCurrentBalance());
//     existingAccount.setOpeningAmount(account.getOpeningAmount());
//     this.modelRepo.save(existingAccount);

//        return existingAccount;
//    }

   // 8. 	Delete an account by account number (2 points)
   @DeleteMapping(value = "/{acc}")
   @ApiOperation(value = "Delete an account by account number")
   public String deleteAccountWithNumber(@ApiParam(value = "account number") @PathVariable ("acc") long acc){

       Account existingAccount = this.modelRepo.findByAccountNumber(acc);
       this.modelRepo.delete(existingAccount);
       return "Account has been deleted successfully";
   }

   // GET account by accountNumber   
   // @GetMapping("/number/{accnumber}")
   // public Account getAccountByAccNumber(@PathVariable (value = "accnumber") long accnumber) {
   //    return this.modelRepo.findByAccountNumber(accnumber);
      
   // }
   






}