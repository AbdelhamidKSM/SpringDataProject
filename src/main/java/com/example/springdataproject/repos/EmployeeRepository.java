package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Departement;
import com.example.springdataproject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findFirstById (Long id );

    // this is named query because there is  a param to enter within  the request .
    @Query("SELECT em FROM Employee em JOIN em.departement d JOIN d.country c WHERE c.name = :countryName")
    // jpql query
    //@Query(value = "SELECT em.* FROM Employee em JOIN departement d ON em.departement_id = d.id JOIN country c ON d.country_id = c.id WHERE c.name = :countryName", nativeQuery = true)// native does not work , i dnw why ?!
    List<Employee> findEmployeesByCountry(@Param("countryName") String countryName);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.departement= :department WHERE e.id = :id")
    void updateEmployeeDepartment(@Param("id") Long id, @Param("department") Departement department);

    @Transactional
    @Modifying
    @Query ("DELETE  from  Employee  e where  e.departement=:dpt")
    int deleteEmployeesByDepartement(@Param("dpt") Departement dpt);

}
