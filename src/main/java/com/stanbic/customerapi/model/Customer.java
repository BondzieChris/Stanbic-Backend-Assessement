package com.stanbic.customerapi.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// validation

@Entity
@Table(name = "customers")
@ApiModel(description = "All details about a Customer")
public class Customer {

   	// Fields
   @Id
   @ApiModelProperty(notes = "The database generated customer ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	
   @NotNull
   @ApiModelProperty(notes = "The Name of Customer")
	private String name;
	
	@Column( unique = true)
	@Email
   @NotNull(message = "Please enter your email")
   @ApiModelProperty(notes = "The email of Customer")
	private String email;

	@Column(name = "phone_number", unique = true)
   @NotNull(message = "Please enter your email")
   @Size(min = 10)
   @ApiModelProperty(notes = "The Customer's phone number")
	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
   @NotNull(message = "Date of Birth is required")
   @ApiModelProperty(notes = "The Customer's Date of Birth")
   private Date dateOfBirth;
   
   @CreationTimestamp
   @JsonFormat(pattern = "yyyy-MM-dd")
   @ApiModelProperty(notes = "The Date Customer was added")
	private Date createdAt;

   @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JsonIgnore
   @ApiModelProperty(notes = "Auto-filled customers relationship with accounts")
   List<Account> accounts;


   // METHODS

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public Date getDateOfBirth() {
      return this.dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public Date getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   

   public List<Account> getAccounts() {
      return this.accounts;
   }

   public void setAccounts(List<Account> accounts) {
      this.accounts = accounts;
   }


   public Customer(String name, String email, String phoneNumber, Date dateOfBirth) {
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
   }
   

   public Customer() {
   }


}