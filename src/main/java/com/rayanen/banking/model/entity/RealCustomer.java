package com.rayanen.banking.model.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class RealCustomer extends  Customer{



    private String lastName;
    @NotNull(message = "کد ملی  را وارد نکرده اید ")

    @Column(unique = true)
    private  String nationalCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
