package com.stanbic.customerapi.controller;
import java.util.List;
import java.util.UUID;

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

import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.repository.AccountRepository;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

   @Autowired
   private AccountRepository modelRepo;

   // get all users
   @GetMapping
   public List<Account> getAllAccounts() {
       return this.modelRepo.findAll();
   }

   // get user by id
   @GetMapping("/{id}")
   public Account getAccountById(@PathVariable (value = "id") UUID id) {
       return this.modelRepo.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));
   }

   // create user
   @PostMapping
   public Account createAccount(@Valid @RequestBody Account user) {
       return this.modelRepo.save(user);
   }

   // update user
   @PutMapping("/{id}")
   public Account updateAccount(@Valid @RequestBody Account user, @PathVariable ("id") UUID id) {
       Account existingAccount = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));
   //    existingAccount.setFirstName(user.getFirstName());
       return existingAccount;
   }

   // delete user by id
   @DeleteMapping("/{id}")
   public ResponseEntity<Account> deleteAccount(@PathVariable ("id") UUID id){
       Account existingAccount = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Account not found with id :" + id));
       this.modelRepo.delete(existingAccount);
       return ResponseEntity.ok().build();
   }
}