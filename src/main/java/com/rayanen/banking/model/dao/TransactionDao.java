package com.rayanen.banking.model.dao;


import com.rayanen.banking.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction,Integer> {
}
