package com.stanbic.customerapi.controller;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;

import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.repository.AccountRepository;
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

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

   @Autowired
   private AccountRepository modelRepo;


   // get all accounts
   @GetMapping(value = "")
   public List<Account> getAllAccounts() {
       return this.modelRepo.findAll();
   }

   // get account by id
   @GetMapping(value = "/{id}")
   public Account getAccountById(@PathVariable(name = "id") long id) {

       return this.modelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));

   }

   // create account
   @PostMapping
   public Account createAccount(@Valid @RequestBody Account account) {

        Date date = new Date();
        long accnumber = date.getTime();
        account.setAccountNumber(accnumber); 
        return this.modelRepo.save(account);
   }

   // update account
   @PutMapping(value = "/{id}")
   public Account updateAccount(@Valid @RequestBody Account account, @PathVariable (name = "id") long id) {
       Account existingAccount = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));

    existingAccount.setCurrentBalance(account.getCurrentBalance());
    existingAccount.setOpeningAmount(account.getOpeningAmount());
    this.modelRepo.save(existingAccount);

       return existingAccount;
   }

   // delete account by id
   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Account> deleteAccount(@PathVariable ("id") long id){
       Account existingAccount = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));
       this.modelRepo.delete(existingAccount);
       return ResponseEntity.ok().build();
   }

   // get account by accountNumber
   @GetMapping("/number/{accnumber}")
   public Account getAccountByAccNumber(@PathVariable (value = "accnumber") long accnumber) {

      return this.modelRepo.findByAccountNumber(accnumber);
      
   }
   



}