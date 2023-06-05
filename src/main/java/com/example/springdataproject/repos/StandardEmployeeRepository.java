package com.example.springdataproject.repos;

import com.example.springdataproject.entities.StandardEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardEmployeeRepository extends JpaRepository<StandardEmployee, Long> {
}