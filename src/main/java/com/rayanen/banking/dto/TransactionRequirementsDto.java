package com.rayanen.banking.dto;

import java.math.BigDecimal;

public class TransactionRequirementsDto {

    private Integer savingAccountNumber;

    private BigDecimal amount;


    public Integer getSavingAccountNumber() {
        return savingAccountNumber;
    }

    public void setSavingAccountNumber(Integer savingAccountNumber) {
        this.savingAccountNumber = savingAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
