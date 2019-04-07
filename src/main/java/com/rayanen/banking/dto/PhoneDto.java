package com.rayanen.banking.dto;




import javax.persistence.Id;

public class PhoneDto {
    @Id
    private Integer id;

    private String number;


    private PhoneType type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String num) {
        this.number = num;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }
}
