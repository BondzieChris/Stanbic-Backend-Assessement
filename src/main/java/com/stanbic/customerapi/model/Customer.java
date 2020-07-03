package com.stanbic.customerapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

// validation

@Entity
@Table(name = "customers")
public class Customer {

   	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	
	@NotNull
	private String name;
	
	@Column( unique = true)
	@Email
   @NotNull(message = "Please enter your email")
	private String email;

	@Column(name = "phone_number", unique = true)
	@NotNull(message = "Please enter your email")
	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
   @NotNull(message = "Date of Birth is required")
   private Date dateOfBirth;
   
   @CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
   List<Account> comments = new ArrayList<>();


   // Methods

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

   public List<Account> getComments() {
      return this.comments;
   }

   public void setComments(List<Account> comments) {
      this.comments = comments;
   }


   public Customer(String name, String email, String phoneNumber, Date dateOfBirth, List<Account> comments) {
      this.name = name;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.dateOfBirth = dateOfBirth;
      this.comments = comments;
   }
   

   public Customer() {
   }


}