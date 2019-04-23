package com.rayanen.banking.model.dao;

import com.rayanen.banking.model.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilitiesDao extends JpaRepository<Facilities,Integer> {
}
