package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    // get all employees from database
    public List<Employee> allEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }
    // add employee to database

    public Employee addEmployee(String id, String name, String age, String salary) {
        int iAge = Integer.parseInt(age);
        int iSalary = Integer.parseInt(salary);
        Employee employee = employeeRepository.insert(new Employee(id, name, iAge, iSalary));
        return employee;
    }

    // edit employee
    public void editEmployee(String id, String name, String age, String salary, String employeeId) {
        int iAge = Integer.parseInt(age);
        int iSalary = Integer.parseInt(salary); 
        
        Query select = Query.query(Criteria.where("id").is(employeeId));
        Update update = new Update();
        update.set("id", id);
        update.set("name", name);
        update.set("age", iAge);
        update.set("iSalary", iSalary);

        mongoTemplate.update(Employee.class)
        .matching(select)
        .apply(update)
        .first();
    }

    //find employee with most age
    
    //delete employee
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id);
        ObjectId employeeId = employee.getObjectId();
        employeeRepository.deleteById(employeeId);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }
}
