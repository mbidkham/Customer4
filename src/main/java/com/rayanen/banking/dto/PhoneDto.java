package com.rayanen.banking.dto;




import com.rayanen.banking.utility.Annotations.NotMap;

import javax.persistence.Id;
import javax.persistence.Version;

public class PhoneDto {
    @Id
    private Integer id;

    @NotMap
    @Version
    private  Integer version;

    private String number;


    private PhoneType type;

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
