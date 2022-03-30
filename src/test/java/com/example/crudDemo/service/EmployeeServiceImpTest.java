package com.example.crudDemo.service;

import com.example.crudDemo.dao.EmployeeRepository;
import com.example.crudDemo.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImpTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void setup(){
        this.employeeService = new EmployeeServiceImp(this.employeeRepository);
    }
    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll();
    }

    @Test
    @Rollback(value = false)
   void findById() {
       Employee employee = employeeService.findById(1);
       Optional<Employee> employee1 = employeeRepository.findById(1);
       if(employee1.isPresent()){
           Assertions.assertThat(employee1.get()).isEqualTo(employee);
       }

    }

}