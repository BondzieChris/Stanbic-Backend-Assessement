package com.stanbic.customerapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.stanbic.customerapi.exception.ResourceNotFoundException;
import com.stanbic.customerapi.model.Account;
import com.stanbic.customerapi.model.Customer;
import com.stanbic.customerapi.repository.CustomerRepository;
import com.stanbic.customerapi.twilio.SmsRequest;
import com.stanbic.customerapi.twilio.SmsSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "api/v1/customers")
@Api(value="Customers API section", description="Section for handler actions of customers")
public class CustomerController implements SmsSender {


   @Autowired
   private CustomerRepository modelRepo;

   @Autowired
   private SmsSender smsSender;

   // 3. 	Retrieve all customers (5 points)
   @GetMapping
   @ApiOperation(value = "Retrieve all customers", response = List.class)
   public List<Customer> getAllCustomers() {
       return this.modelRepo.findAll();
   }

   
   // 4. 	Retrieve a customer’s info by Email or phone number (3 points)
   @GetMapping("/info")
   @ApiOperation(value = "Retrieve a customer’s info by Email or phone number")
   public Customer getCustomerEmail(@ApiParam(value = "Customer ID to retrieve Customer Information", required = true) @RequestParam(name = "value") String value) {

    
       Customer customer = this.modelRepo.findByEmail(value);

       if(customer == null)
            customer = this.modelRepo.findByPhoneNumber(value);
       
       return customer;
   }


   // 1. 	Add a new customer (2 points) 
   @PostMapping
   @ApiOperation(value = "Add a new Customer")
   public Customer createCustomer( @ApiParam(value = "Customer object store in database table", required = true) @Valid @RequestBody Customer customer) {


    // return "route has been hit";
    // SMS with twilio. Only verifies numbers will receivce SMS becuase the twilio account is free
       SmsRequest smsRequest = new SmsRequest(customer.getPhoneNumber(), "Congratulation!! You are now a client of Stanbic Bank");
       this.sendSms(smsRequest);
       
       return this.modelRepo.save(customer);
   }


   // 5. 	Update Customer info (2 points)
   @PutMapping("/{id}")
   @ApiOperation(value = "Update information of existing Customer")
   public Customer updateCustomer(@ApiParam(value = "New Customer information", required = true) @Valid @RequestBody Customer customer, @ApiParam(value = "ID of the customer to be updated", required = true) @PathVariable("id") long id) {
       
    Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
            existingCustomer.setName(customer.getName());
            existingCustomer.setDateOfBirth(customer.getDateOfBirth());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());

            // SmsRequest smsRequest = new SmsRequest(existingCustomer.getPhoneNumber(), "You information at Stanbic has been updated");
            // this.sendSms(smsRequest);
            return this.modelRepo.save(existingCustomer);
   }


   // 9. 	Delete a customer and all linked accounts. (3 points)
   @DeleteMapping("/{id}")
   @ApiOperation(value = "Delete a customer and all linked accounts")
   public Map<String,String> deleteCustomer(@ApiParam(value = "ID of customer to be deleted", required = true ) @PathVariable(name = "id") long id){

       Customer existingCustomer = this.modelRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id :" + id));
           
        SmsRequest smsRequest = new SmsRequest(existingCustomer.getPhoneNumber(), "You're sorry to inform you that, you're no more a client of Stanbic Bank");
        this.sendSms(smsRequest);
       this.modelRepo.delete(existingCustomer);
    
        Map<String,String> response = new HashMap<String, String>();
        response.put("done", "Customer together with all accounts deleted");
        return response;
     }
 

   
//    7. 	Retrieve all accounts of a customer using email or phone number. (5 points)
   @GetMapping(value = "/accounts")
   @ApiOperation(value = "Retrieve all accounts of a customer using email or phone number")
   public List<Account> getCustomerAccounts(@ApiParam(value = "Email or Phone number of customer") @RequestParam(name = "value") String value) {

    
    Customer customer = this.modelRepo.findByEmail(value);

    if(customer == null)
         customer = this.modelRepo.findByPhoneNumber(value);

    return customer.getAccounts();

   }


// 2. 	Add a workflow to notify the customer whenever an action like add new account or update customer info is done.
@Override
public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
}

}