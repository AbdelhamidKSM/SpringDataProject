package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Contact;
import com.example.springdataproject.entities.Folder;
import com.example.springdataproject.entities.StandardEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandardEmployeeRepository extends JpaRepository<StandardEmployee, Long> {

    StandardEmployee findStandardEmployeeByContact(Contact contact);

    StandardEmployee findStandardEmployeeByProfileContainingIgnoreCase(String profile);

    StandardEmployee findFirstByContact(Contact contact);

    StandardEmployee findStandardEmployeeByContactAndFolder(Contact contact, Folder folder);


}