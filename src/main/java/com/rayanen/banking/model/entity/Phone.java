package com.rayanen.banking.model.entity;

import com.rayanen.banking.dto.PhoneType;

import javax.persistence.*;

@Table(name =" BID_PHONE")
@Entity
public class Phone {

    @Id
    @GeneratedValue
    private Integer id;

    private String number;

    private PhoneType type;

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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
