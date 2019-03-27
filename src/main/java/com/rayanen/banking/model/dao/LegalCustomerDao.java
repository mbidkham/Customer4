package com.rayanen.banking.model.dao;

import com.rayanen.banking.model.entity.LegalCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LegalCustomerDao extends JpaRepository<LegalCustomer,Integer> {

    @Query("select c from LegalCustomer c WHERE UPPER(c.name) LIKE  %:name%")
     List<LegalCustomer> findByName(@Param("name") String name);
     LegalCustomer findByLegalCode(  String legalCode);
     Optional<LegalCustomer> findById(Integer id);


}
