package com.rayanen.banking.dto;

import com.rayanen.banking.model.entity.Phone;
import com.rayanen.banking.utility.Annotations.MapTo;
import com.rayanen.banking.utility.Annotations.NotMap;

import javax.persistence.*;
import java.util.List;

public class ContactDto {

    @Id
    private Integer id;

    @Version

    private Integer version;

    private String email;

    @MapTo(targetEntity = Phone.class)
    private List<PhoneDto> phones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }



}
