package com.rayanen.banking.dto;

public  class CustomerDto {

    private String name;
    private AddressDto address;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }


    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public AddressDto getAddress() {
        return address;
    }


}