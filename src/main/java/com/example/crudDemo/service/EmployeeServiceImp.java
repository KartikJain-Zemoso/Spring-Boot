package com.example.crudDemo.service;


import com.example.crudDemo.dao.EmployeeDAO;
import com.example.crudDemo.dao.EmployeeRepository;
import com.example.crudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;
    public EmployeeServiceImp( EmployeeRepository employeeRepository){
        this.employeeRepository =employeeRepository;
    }
    @Override

    public List<Employee> findAll() {
       List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override

    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if(result.isPresent()) {
            employee = result.get();
        }
        else{
            throw new RuntimeException("Didn't find employee Id -"+ id);
        }
        return  employee;
    }

    @Override

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
