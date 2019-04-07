package com.rayanen.banking.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

public class AddressDto {

    @Id
    @GeneratedValue
    private Integer id;

    private String street;

    private String city;

    private String province;

    private String postalCode;

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

    public void setStreet(String street) {
        this.street = street;

    }

    public void setCity(String city) {
        this.city = city;

    }

    public void setProvince(String province) {
        this.province = province;

    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;

    }


    public String getStreet() {
        return street;
    }


    public String getCity() {
        return city;
    }


    public String getProvince() {
        return province;
    }


    public String getPostalCode() {
        return postalCode;
    }

    }

