package com.rayanen.banking.model.dao;


import com.rayanen.banking.model.entity.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingAccountDao  extends JpaRepository<SavingAccount,Integer> {
     SavingAccount findByAccountNumber(Integer accountNumber);
     List<SavingAccount> findAll();


}
