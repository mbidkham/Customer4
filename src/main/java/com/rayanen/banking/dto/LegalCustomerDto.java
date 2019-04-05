package com.rayanen.banking.dto;

public class LegalCustomerDto extends CustomerDto {

    private String legalCode;
    private  String phone;


    public String getLegalCode() {
        return legalCode;
    }

    public void setLegalCode(String legalCode) {
        this.legalCode = legalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }
}
