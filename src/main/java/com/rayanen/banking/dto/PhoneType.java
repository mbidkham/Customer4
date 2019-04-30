package com.rayanen.banking.dto;

public enum PhoneType {
            MOBILE("همراه"), HOME("ثابت");
    private String value;

    PhoneType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
