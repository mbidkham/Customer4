package com.rayanen.banking.model.dao;

import com.rayanen.banking.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer,Integer> {


}
