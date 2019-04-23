package com.rayanen.banking.model.entity;

import com.rayanen.banking.dto.FacilityType;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name =" BID_FACILITIES")
@Entity
public class Facilities {

    @Id
    @GeneratedValue
    private Integer id;

    @Version
    private Integer version;

    private BigDecimal amount;

    //شماره مشتری همان کد ملی / کد ثبت شرکت در نظر گرفته شده
    private Integer customerCode;

    private FacilityType facilityType;


    public Integer getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Integer customerCode) {
        this.customerCode = customerCode;
    }




    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
