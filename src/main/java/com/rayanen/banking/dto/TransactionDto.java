package com.rayanen.banking.dto;

import com.rayanen.banking.model.entity.Phone;
import com.rayanen.banking.model.entity.Transaction;
import com.rayanen.banking.utility.Annotations.MapTo;
import com.rayanen.banking.utility.Annotations.NotMap;

import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;

public class TransactionDto {

    @Id
    private Integer id;


    @Version
    private Integer version;

    private BigDecimal amount;

    private Date transactionDate;


    private TransactionType transactionType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
