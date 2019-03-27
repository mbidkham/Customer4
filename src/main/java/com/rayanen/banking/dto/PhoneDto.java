package com.rayanen.banking.dto;

public class PhoneDto {
    private String num;
    private PhoneType type;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
