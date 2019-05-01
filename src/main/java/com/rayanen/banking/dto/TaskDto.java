package com.rayanen.banking.dto;

import java.math.BigDecimal;

public class TaskDto {

    private String taskId;

    private Integer customerNumber;

    private String name;

    private String formKey;

    private BigDecimal amount;

    private FacilityType facilityType;

    private FacilitySituation facilitySituation = FacilitySituation.NOTSEEN;

    public FacilitySituation getFacilitySituation() {
        return facilitySituation;
    }

    public void setFacilitySituation(FacilitySituation facilitySituation) {
        this.facilitySituation = facilitySituation;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public TaskDto() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
