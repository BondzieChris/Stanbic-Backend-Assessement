package com.stanbic.customerapi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "accounts")
public class Account {

// FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    private long accountNumber;

    @NotNull(message = "Please Enter opening amount")
    private double openingAmount;

    @NotNull(message = "Please Enter current balance")
    private double currentBalance;

     // @CreationTimestamp is for auto filled
   @CreationTimestamp
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date createdAt;

   @ManyToOne
   @JoinColumn(name = "customer_id",referencedColumnName = "id")
   @JsonIgnoreProperties(value = {"accounts"})
   private Customer customer;

   
   //    METHODS
       public Customer getCustomer() {
           return this.customer;
       }
    
       public void setCustomer(Customer customer) {
           this.customer = customer;
       }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getOpeningAmount() {
        return this.openingAmount;
    }

    public void setOpeningAmount(double openingAmount) {
        this.openingAmount = openingAmount;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Account() {
    }
   
    public Account(double openingAmount, double currentBalance) {
        this.openingAmount = openingAmount;
        this.currentBalance = currentBalance;
    }

}