package com.rayanen.banking.dto;

import java.util.List;

public class RealCustomerDto extends CustomerDto {


    private String lastName;
    private String nationalCode;
    private ContactDto contactDto;


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

    public ContactDto getContactDto() {
        return contactDto;
    }

    public void setContactDto(ContactDto contactDto) {
        this.contactDto = contactDto;

    }
}
