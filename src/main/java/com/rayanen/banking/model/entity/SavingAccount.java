package com.rayanen.banking.model.entity;

import com.rayanen.banking.dto.FacilitiesDto;
import com.rayanen.banking.dto.TransactionDto;
import com.rayanen.banking.utility.Annotations.MapTo;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;
@Table(name =" BID_ACCOUNTS")
@Entity
public class SavingAccount {



    @Id
    @GeneratedValue
    private Integer id;
    @Version
    private Integer version;

    @OneToMany
    @MapTo(targetEntity = TransactionDto.class)
    private List<Transaction> transactions;

    private Integer accountNumber;

    private BigDecimal balance = new BigDecimal(5000) ;

    @OneToMany
    @MapTo(targetEntity = FacilitiesDto.class)
    private List<Facilities> facilities;


    private BigDecimal minBalance=balance;

    private BigDecimal sumOfMinBalances = new BigDecimal(0);

    private BigDecimal monthlyProfit = new BigDecimal(0);


    public BigDecimal getSumOfMinBalances() {
        return sumOfMinBalances;
    }

    public void setSumOfMinBalances(BigDecimal sumOfMinBalances) {
        this.sumOfMinBalances = sumOfMinBalances;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(BigDecimal minBalance) {
        this.minBalance = minBalance;
    }

    public BigDecimal getMonthlyProfit() {
        return monthlyProfit;
    }

    public void setMonthlyProfit(BigDecimal monthlyProfit) {
        this.monthlyProfit = monthlyProfit;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }



    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }



}
