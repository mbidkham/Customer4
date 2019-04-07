package com.rayanen.banking.dto;

import javax.persistence.Id;

import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.List;

public class SavingAccountDto {

    @Id
    private Integer id;

    @Version
    private Integer version;


    private List<TransactionDto> transactions;

    private Integer accountNumber;

    private BigDecimal balance = new BigDecimal(5000) ;

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

    public void setTransactions(List<TransactionDto> transactions) {
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

    public List<TransactionDto> getTransactions() {
        return transactions;
    }



    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

}
