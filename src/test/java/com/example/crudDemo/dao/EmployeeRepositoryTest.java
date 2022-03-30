package com.example.crudDemo.dao;

import com.example.crudDemo.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Junit test to save employee

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveEmployeeTest(){
        Employee employee = new Employee("Abc", "Pqr","abc@gmail.com");

        employeeRepository.save(employee);

        org.assertj.core.api.Assertions.assertThat(employee.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest(){
        Employee employee = employeeRepository.findById(1).get();

        org.assertj.core.api.Assertions.assertThat(employee.getId()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void getListEmployeeTest(){
       List<Employee> employees= employeeRepository.findAll();

        org.assertj.core.api.Assertions.assertThat(employees.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest(){
        Employee employee = employeeRepository.findById(13).get();
        employee.setEmail("ram@gmail.com");
      Employee updatedEmployee = employeeRepository.save(employee);
        org.assertj.core.api.Assertions.assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        Employee employee = employeeRepository.findById(13).get();
         employeeRepository.delete(employee);
         Employee employee1 = null;

        Optional<Employee> optionalEmployee = employeeRepository.findByEmail("ram@gmail.com");
        if(optionalEmployee.isPresent()){
            employee1=optionalEmployee.get();
        }
        org.assertj.core.api.Assertions.assertThat(employee1).isNull();
    }

}