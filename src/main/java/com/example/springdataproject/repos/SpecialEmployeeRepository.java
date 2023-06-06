package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Contact;
import com.example.springdataproject.entities.Folder;
import com.example.springdataproject.entities.SpecialEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialEmployeeRepository extends JpaRepository<SpecialEmployee, Long> {

    SpecialEmployee findSpecialEmployeeByContact(Contact contact);

    SpecialEmployee findSpecialEmployeeByProfileContainingIgnoreCase(String profile);

    SpecialEmployee findFirstByContact(Contact contact);

    SpecialEmployee findSpecialEmployeeByContactAndFolder(Contact contact, Folder folder);

    @Query("SELECT se FROM SpecialEmployee se WHERE se.profile = :profile")
    List<SpecialEmployee> findSpecialEmployeesByProfile(@Param("profile") String profile);


    @Query("SELECT COUNT(se) FROM SpecialEmployee se WHERE se.profile LIKE %:profile%")
    Long countSpecialEmployeeByProfileLike (@Param("profile") String profile);

    @Query("SELECT se FROM SpecialEmployee  se WHERE se.tjm BETWEEN :minTjm AND :maxTjm ")
    List<SpecialEmployee> findSpecialEmployeeByTjmRange (@Param("minTjm") Double minTjm , @Param("maxTjm") Double maxTjm );

    @Query("Select se From SpecialEmployee  se where se.departement.name =:depName ")
    List<SpecialEmployee> findSpecialEmployeesByDepartmentName (@Param("depName") String depName);
}