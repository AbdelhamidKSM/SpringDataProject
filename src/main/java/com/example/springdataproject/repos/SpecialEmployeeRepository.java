package com.example.springdataproject.repos;

import com.example.springdataproject.entities.SpecialEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialEmployeeRepository extends JpaRepository<SpecialEmployee, Long> {
}