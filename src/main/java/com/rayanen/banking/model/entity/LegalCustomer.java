package com.rayanen.banking.model.entity;



import com.rayanen.banking.utility.Annotations.MapTo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Table(name =" BID_legal")
@Entity
public class LegalCustomer extends Customer {

    @NotNull(message = "کد ثبت شرکت  را وارد نکرده اید ")
    @Column(unique = true)
    private String legalCode;

    @MapTo
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


}
