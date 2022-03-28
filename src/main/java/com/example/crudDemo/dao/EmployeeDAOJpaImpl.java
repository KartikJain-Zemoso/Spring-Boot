package com.example.crudDemo.dao;

import com.example.crudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }
    @Override
    public List<Employee> findAll() {
        Query theQuery =
                entityManager.createQuery("from Employee");

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;

    }

    @Override
    public Employee findById(int id) {
        // get employee
        Employee employee =
                entityManager.find(Employee.class, id);

        // return employee
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Employee dbEmployee = entityManager.merge(employee);

        // update with id from db ... so we can get generated id for save/insert
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(int id) {
        Query theQuery = entityManager.createQuery(
                "delete from Employee where id=:employeeId");

        theQuery.setParameter("employeeId", id);

        theQuery.executeUpdate();
    }
}
