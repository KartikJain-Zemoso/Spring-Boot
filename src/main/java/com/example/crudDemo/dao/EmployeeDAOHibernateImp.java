package com.example.crudDemo.dao;

import com.example.crudDemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImp implements EmployeeDAO{


    private EntityManager entityManager;


    @Autowired
    public EmployeeDAOHibernateImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override

    public List<Employee> findAll() {

        //get the current hibernate session
        Session session = entityManager.unwrap(Session.class);

        // create query
        Query<Employee> query = session.createQuery("from Employee", Employee.class);

        //execute query
        List<Employee> employees = query.getResultList();



        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        Employee theEmployee =
                currentSession.get(Employee.class, theId);

        // return the employee
        return theEmployee;
    }


    @Override
    public void save(Employee theEmployee) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(theEmployee);
    }


    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery =
                currentSession.createQuery(
                        "delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();
    }
}
