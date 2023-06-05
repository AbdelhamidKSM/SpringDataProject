package com.example.springdataproject.repos;

import com.example.springdataproject.entities.Contact;
import com.example.springdataproject.entities.Folder;
import com.example.springdataproject.entities.SpecialEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialEmployeeRepository extends JpaRepository<SpecialEmployee, Long> {

    SpecialEmployee findSpecialEmployeeByContact(Contact contact);

    SpecialEmployee findSpecialEmployeeByProfileContainingIgnoreCase(String profile);

    SpecialEmployee findFirstByContact(Contact contact);

    SpecialEmployee findSpecialEmployeeByContactAndFolder(Contact contact, Folder folder);
}