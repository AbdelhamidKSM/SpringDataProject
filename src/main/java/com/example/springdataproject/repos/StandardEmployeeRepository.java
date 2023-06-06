package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Contact;
import com.example.springdataproject.entities.Folder;
import com.example.springdataproject.entities.StandardEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandardEmployeeRepository extends JpaRepository<StandardEmployee, Long> {

    StandardEmployee findStandardEmployeeByContact(Contact contact);

    StandardEmployee findStandardEmployeeByProfileContainingIgnoreCase(String profile);

    StandardEmployee findFirstByContact(Contact contact);

    StandardEmployee findStandardEmployeeByContactAndFolder(Contact contact, Folder folder);

    @Query(nativeQuery = true , name = "Employee.findByProfile")
    List<StandardEmployee> findByProfile (@Param("profile") String profile);

    @Query("SELECT ste FROM StandardEmployee ste WHERE ste.profile LIKE %:profile% and  ste.contact.email=:email")
    List<StandardEmployee> findByEmailAndProfile (@Param("email") String email , @Param("profile") String profile);

    @Query(value ="SELECT MAX (salary) from standard_employee" , nativeQuery = true)
    Double findMaxSalary ();
}