package com.example.springdataproject.repostest;

import com.example.springdataproject.entities.*;
import com.example.springdataproject.entities.StandardEmployee.SeniorityFirstLevel;
import com.example.springdataproject.repos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.springdataproject.entities.SpecialEmployee.SenioritySecondLevel.SENIOR;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {


    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartementRepository departementRepository;

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

    /**
     * working with this entity will help us to understand the relationships between all the tables in this case we are using special Employee
     */
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
    /**
     * working with Queries
     */
    @Test
     void sqlQueries_test (){
        // create a special employee
        SpecialEmployee  specialEmployee = SpecialEmployee.builder()
                .profile("Java Architecture")
                .tjm(3000D)
                .seniority(SENIOR)
                .build();

        Contact specialContact = Contact.builder()
                .name("Mohammed ")
                .email("Mohammed@gmail.com")
                .mobile("0643333554")
                .build();

        Departement departement = Departement.builder()
                .name("IT")
                .build();

        // create a Standard Employee

        StandardEmployee  standardEmployee = StandardEmployee.builder()
                .profile("Java Senior")
                .salary(150000D)
                .seniority(SeniorityFirstLevel.JUNIOR)
                .build();

        Contact standardContact = Contact.builder()
                .name("Mohammed ")
                .email("Mohammed@gmail.com")
                .mobile("0643333554")
                .build();

        Departement stdDepartement = Departement.builder()
                .name("commerce")
                .build();
        Country country = Country.builder()
                .name("China")
                .departements(Stream.of(stdDepartement,departement).collect(Collectors.toSet()))
                .build();
        // saving employees
        specialEmployee.setContact(specialContact);
        specialEmployee.setDepartement(departement);
        standardEmployee.setContact(standardContact);

        specialEmployeeRepository.save(specialEmployee);
        standardEmployeeRepository.save(standardEmployee);

        // Assertion SpecialEmployee
        List<SpecialEmployee> tjmByEmail = specialEmployeeRepository.findSpecialEmployeesByProfile(specialEmployee.getProfile());
        Long specialEmployeesByProfile = specialEmployeeRepository.countSpecialEmployeeByProfileLike(specialEmployee.getProfile());
        List<SpecialEmployee> specialEmployeesByTjmRange = specialEmployeeRepository.findSpecialEmployeeByTjmRange(2700D ,3100D);
        List<SpecialEmployee> specialEmployeesByDepartmentName= specialEmployeeRepository.findSpecialEmployeesByDepartmentName("IT");

        // Assertions StandardEmployee
        List<StandardEmployee> standardEmployeesByProfile = standardEmployeeRepository.findByProfile(standardEmployee.getProfile());
        List<StandardEmployee> standardEmployeesByEmailAndProfile = standardEmployeeRepository.findByEmailAndProfile( specialEmployee.getContact().getEmail(),standardEmployee.getProfile());
        List<Employee> employeesCountry = employeeRepository.findEmployeesByCountry(country.getName());
        Double maxSalary = standardEmployeeRepository.findMaxSalary();

        // assertions
        assertNotNull(tjmByEmail);
        assertNotNull(specialEmployeesByProfile);
        assertNotNull(specialEmployeesByTjmRange);
        assertNotNull(specialEmployeesByDepartmentName);
        assertNotNull(standardEmployeesByProfile);
        assertNotNull(standardEmployeesByEmailAndProfile);
        assertNotNull(employeesCountry);
        assertNotNull(maxSalary);

    }

    // work with @Transactional and @Modifying

    @Test
    void test_Data_Modification_Operators (){
        // create a Standard Employee
        StandardEmployee  standardEmployee = StandardEmployee.builder()
                .profile("Java Senior")
                .salary(150000D)
                .seniority(SeniorityFirstLevel.JUNIOR)
                .build();

        Contact standardContact = Contact.builder()
                .name("Mohammed ")
                .email("Mohammed@gmail.com")
                .mobile("0643333554")
                .build();

        Departement stdDepartement = Departement.builder()
                .name("commerce")
                .build();

        // saving employees
        standardEmployee.setContact(standardContact);
        standardEmployee.setDepartement(stdDepartement);

        employeeRepository.save(standardEmployee);

        /*  modification operators (Update & Delete )  */
         // this the department that we want to change
        Departement changedDepartement = Departement.builder()
                .name("Finance")
                .build();
        departementRepository.save(changedDepartement);
        employeeRepository.updateEmployeeDepartment(standardEmployee.getId(),changedDepartement);

        // assertions
        assertEquals(departementRepository.findById(changedDepartement.getId()).orElseThrow().getId() ,employeeRepository.findFirstById(standardEmployee.getId()).getDepartement().getId());

        // working on delete case
        int deletedRows = employeeRepository.deleteEmployeesByDepartement(changedDepartement);
        assertNotEquals(0 , deletedRows );

    }


    // Pagination and sorting

    @Test
     void test_PaginationAndSorting (){
        int pageNumber =0;
        int sizeNumber =4;
        Sort sort =Sort.by("contact.name").ascending();

        Pageable pageable = PageRequest.of(pageNumber , sizeNumber ,sort);

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        List<Employee> employees = employeePage.getContent();

        assertEquals(pageNumber , employees.size());

    }


}