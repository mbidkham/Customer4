package com.rayanen.banking.dto;

import java.util.List;

public class ContactDto {
    private String email;
    private List<PhoneDto> tel;


    public ContactDto(String mobile, List<PhoneDto> tel, String email) {
        this.email = email;
        this.tel=tel;
    }

    public ContactDto() {
    }

    public List<PhoneDto> getTel() {
        return tel;
    }

    public void setTel(List<PhoneDto> tel) {
        this.tel = tel;
    }



    public void setMobile(String mobile) {
        this.email = email;

    }




    public String getMobile() {
        return email;
    }




}
