package com.example.demo;

import java.util.List;

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

    public Employee addEmployee(Employee employee) {
        employeeRepository.insert(employee);
        return employee;
    }

    // edit employee
    public void editEmployee(Employee employee, String employeeId) {
        Query select = Query.query(Criteria.where("id").is(employeeId));
        Update update = new Update();
        update.set("name", employee.getName());
        update.set("age", employee.getAge());
        update.set("salary", employee.getSalary());

        mongoTemplate.update(Employee.class)
        .matching(select)
        .apply(update)
        .first();
    }
    
    //delete employee
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id);
        ObjectId employeeId = employee.getObjectId();
        employeeRepository.deleteById(employeeId);
    }
}
