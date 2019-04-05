package com.rayanen.banking.model.entity;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class LegalCustomer extends Customer {

    @NotNull(message = "کد ثبت شرکت  را وارد نکرده اید ")
    @Column(unique = true)
    private String legalCode;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getLegalCode() {
        return legalCode;
    }

    public void setLegalCode(String legalCode) {
        this.legalCode = legalCode;
    }
    @Override
    public String toString() {
        return "name: "+ this.getName()+"legalCode: "+"\n"+this.legalCode;
    }
}
