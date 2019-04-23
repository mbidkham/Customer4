package com.rayanen.banking.dto;

public enum FacilityType {

    INSTALLMENTSSALE("فروش اقساطی") ,LOAN("وام");

    private String value;

    FacilityType (String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
