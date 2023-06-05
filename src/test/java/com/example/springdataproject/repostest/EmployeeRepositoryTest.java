package com.example.springdataproject.repostest;

import com.example.springdataproject.entities.*;
import com.example.springdataproject.repos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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



    @Test
    void test_save() {
        Contact contact = Contact.builder()
                .name("Ahmed")
                .email("Ahmed@gmail.com")
                .mobile("06454545454")
                .build();

        Country country = Country.builder()
                .name("Morroco")
                .build();
        countryRepository.save(country);

        Departement departement = Departement.builder()
                .name("IT")
                .build();
        departementRepository.save(departement);
        Employee employee = new Employee();
        employee.setContact(contact);
//        employee.setDepartement(departement);

        employeeRepository.save(employee);

        Folder folder = Folder.builder()
                .acessType("read")
//                .employee(employee)
                .build();
        folderRepository.save(folder);

        // print all the employees
        employeeRepository.findAll().forEach(employee1 -> System.out.println("here is the student########## " + employee1.getContact().getName()));
        folderRepository.findAll().forEach(folder1 -> System.out.println("here is the Acces Type########## " + folder1.getAcessType()));
        departementRepository.findAll().forEach(departement1 -> System.out.println("here is the name of departement ########## " + departement1.getName()));
//        countryRepository.findAll().forEach(country1 -> System.out.println("here is the student########## " + country1.getName() + country1.getDepartements()));

        assertNotNull(employeeRepository.findAll());
    }
}