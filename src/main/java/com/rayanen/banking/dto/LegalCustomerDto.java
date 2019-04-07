package com.rayanen.banking.dto;

import javax.validation.constraints.NotNull;

public class LegalCustomerDto extends CustomerDto {

    @NotNull(message = "کد ثبت شرکت  را وارد نکرده اید ")
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
