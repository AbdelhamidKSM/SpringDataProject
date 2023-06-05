package com.example.springdataproject.repostest;

import com.example.springdataproject.entities.*;
import com.example.springdataproject.entities.StandardEmployee.SeniorityFirstLevel;
import com.example.springdataproject.repos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.springdataproject.entities.SpecialEmployee.SenioritySecondLevel.SENIOR;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private SpecialEmployeeRepository specialEmployeeRepository;
    @Autowired
    private StandardEmployeeRepository standardEmployeeRepository;


    /**
     * working with this entity will help us to understand the relationships between all the tables
     */
    @Test
    void test_save_StandardEmployee() {

        // create a standard employee using the builder
        StandardEmployee standardEmployee = StandardEmployee.builder()
                .profile("Accountant")
                .seniority(SeniorityFirstLevel.OPERATOR)
                .salary(10_000D)
                .build();


        Contact contact = Contact.builder()
                .name("Rachid")
                .email("rachid@gmail.com")
                .mobile("06433335454")
                .build();

        Country country = Country.builder()
                .name("Qatar")
                .build();

        Departement departement = Departement.builder()
                .name("Finance")
                .build();
        departement.setCountry(country);


        standardEmployee.setContact(contact);
        standardEmployee.setDepartement(departement);

        Folder folder = Folder.builder()
                .acessType("read/write")
                .employee(standardEmployee)
                .build();
        standardEmployee.setFolder(folder);

        folderRepository.save(folder);

        // Assertions with contact
        assertNotNull(employeeRepository.findAll());
        StandardEmployee standardEmployeeByContact = standardEmployeeRepository.findStandardEmployeeByContact(contact);
        assertEquals(standardEmployeeByContact.getId(), standardEmployee.getId());

        StandardEmployee employeeFirstByContact = standardEmployeeRepository.findFirstByContact(contact);
        assertEquals(employeeFirstByContact.getContact().getName(), contact.getName());

        StandardEmployee endingWithEmployee = standardEmployeeRepository.findStandardEmployeeByContactAndFolder(standardEmployee.getContact(), standardEmployee.getFolder());
        assertNotNull(endingWithEmployee);
        // Assertions with profile
        StandardEmployee standardEmployeeByProfile = standardEmployeeRepository.findStandardEmployeeByProfileContainingIgnoreCase("acc");
        assertEquals(standardEmployeeByProfile.getSeniority(), standardEmployee.getSeniority());

    }


    @Test
     void test_save_Special_Employee() {
        // Create a Special Employee
        SpecialEmployee  specialEmployee = SpecialEmployee.builder()
                .profile("Java Architecture")
                .tjm(3000D)
                .seniority(SENIOR)
                .build();

        Contact contact = Contact.builder()
                .name("Mohammed ")
                .email("Mohammed@gmail.com")
                .mobile("0643333554")
                .build();

        Country country = Country.builder()
                .name("Egypt")
                .build();

        Departement departement = Departement.builder()
                .name("Management")
                .build();
        departement.setCountry(country);


        specialEmployee.setContact(contact);
        specialEmployee.setDepartement(departement);

        Folder folder = Folder.builder()
                .acessType("Full Control")
                .employee(specialEmployee)
                .build();
        specialEmployee.setFolder(folder);

        folderRepository.save(folder);

        // Assertions with contact
        assertNotNull(employeeRepository.findAll());
        SpecialEmployee specialEmployeeByContact = specialEmployeeRepository.findSpecialEmployeeByContact(contact);

        assertEquals(specialEmployeeByContact.getId(), specialEmployee.getId());

        SpecialEmployee employeeFirstByContact = specialEmployeeRepository.findFirstByContact(contact);
        assertEquals(employeeFirstByContact.getContact().getName(), contact.getName());

        SpecialEmployee endingWithEmployee = specialEmployeeRepository.findSpecialEmployeeByContactAndFolder(specialEmployee.getContact(), specialEmployee.getFolder());
        assertNotNull(endingWithEmployee);
        // Assertions with profile
        SpecialEmployee specialEmployeeByProfile = specialEmployeeRepository.findSpecialEmployeeByProfileContainingIgnoreCase("arch");
        assertEquals(specialEmployeeByProfile.getSeniority(), specialEmployee.getSeniority());

    }

}