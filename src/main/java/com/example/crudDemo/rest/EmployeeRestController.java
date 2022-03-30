package com.example.crudDemo.rest;

import com.example.crudDemo.dao.EmployeeDAO;
import com.example.crudDemo.entity.Employee;
import com.example.crudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private EmployeeService employeeService;


    @Autowired

    public EmployeeRestController( EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        List<Employee> employees = employeeService.findAll();

        return  employees;
    }
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeWithId(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if(employee == null){
            throw  new RuntimeException("Employee id not Found: -"+ employeeId);
        }
        return  employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(),employeeDTO.getEmail());
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = new Employee(employeeDTO.getFirstName(), employeeDTO.getLastName(),employeeDTO.getEmail());
        employeeService.save(employee);

        return employee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if null

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
