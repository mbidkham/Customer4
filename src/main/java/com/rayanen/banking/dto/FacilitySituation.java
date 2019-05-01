package com.rayanen.banking.dto;

public enum FacilitySituation {

    REJECTED("رد شده") ,ACCEPTED("تایید شده") ,NOTSEEN("دیده نشده");

    private String value;

    FacilitySituation (String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
