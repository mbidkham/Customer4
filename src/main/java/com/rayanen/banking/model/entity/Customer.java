package com.rayanen.banking.model.entity;



import com.rayanen.banking.dto.AddressDto;
import com.rayanen.banking.dto.SavingAccountDto;
import com.rayanen.banking.utility.Annotations.MapTo;
import com.rayanen.banking.utility.Annotations.NotMap;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@MappedSuperclass
abstract public  class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    @Version
    private Integer version;

    @NotNull(message = " اسم وارد کنید :")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @MapTo(targetEntity = AddressDto.class)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @MapTo(targetEntity = SavingAccountDto.class)
    private List<SavingAccount> savingAccounts;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(List<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
