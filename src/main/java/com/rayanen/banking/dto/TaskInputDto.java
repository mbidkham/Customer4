package com.rayanen.banking.dto;

import com.rayanen.banking.model.entity.SavingAccount;

import java.math.BigDecimal;
import java.util.List;

public class TaskInputDto {


    private Integer customerNumber;

    private String taskId;

    private BigDecimal amount;

    private  FacilityType facilityType;

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }
}
