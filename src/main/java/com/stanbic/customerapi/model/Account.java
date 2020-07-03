package com.stanbic.customerapi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "accounts")
public class Account {

    // Fields
    @Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "account_number", updatable = false, nullable = false)
	private UUID accountNumber;

    @NotBlank(message = "Please Enter opening amount")
    private double openingAmount;

    @NotBlank(message = "Please Enter opening amount")
    private double currentBalance;

 // @CreationTimestamp is for auto filled
   @CreationTimestamp
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date createdAt;


//    Methods
    public UUID getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(UUID accountNumber) {
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

    public Account(double openingAmount, double currentBalance) {
        this.openingAmount = openingAmount;
        this.currentBalance = currentBalance;
    }

    public Account() {
    }


}