package com.rayanen.banking.dto;

import com.rayanen.banking.model.entity.Contact;
import com.rayanen.banking.utility.Annotations.MapTo;

import javax.validation.constraints.NotNull;

public class RealCustomerDto extends CustomerDto {


    private String lastName;

    @NotNull(message = "کد ملی  را وارد نکرده اید ")
    private  String nationalCode;

    @MapTo(targetEntity = Contact.class)
    private ContactDto contact;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;

    }

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;

    }
}
