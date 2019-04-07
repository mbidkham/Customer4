package com.rayanen.banking.dto;


import com.rayanen.banking.utility.Annotations.MapTo;

import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.List;

public abstract class CustomerDto {


    private Integer id;

    @NotNull(message = " اسم وارد کنید :")
    private String name;

    @Version
    private Integer version;

    @MapTo
    private AddressDto address;

    private List<SavingAccountDto> savingAccounts;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<SavingAccountDto> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(List<SavingAccountDto> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



