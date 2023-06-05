package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}