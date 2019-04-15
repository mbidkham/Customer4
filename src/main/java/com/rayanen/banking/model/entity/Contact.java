package com.rayanen.banking.model.entity;



import com.rayanen.banking.dto.PhoneDto;
import com.rayanen.banking.utility.Annotations.MapTo;

import javax.persistence.*;

import java.util.List;
@Table(name =" BID_CONTACT")
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @MapTo(targetEntity = PhoneDto.class)
    private List<Phone> phones;


    @Version
    private Integer version;

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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
