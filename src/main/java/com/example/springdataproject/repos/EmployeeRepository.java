package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee ,Long> {

    @Query("SELECT em FROM Employee em JOIN em.departement d JOIN d.country c WHERE c.name = :countryName")// jpql query
//@Query(value = "SELECT em.* FROM Employee em JOIN departement d ON em.departement_id = d.id JOIN country c ON d.country_id = c.id WHERE c.name = :countryName", nativeQuery = true)// native does not work , i dnw why ?!
List<Employee> findEmployeesByCountry(@Param("countryName") String countryName);



}
