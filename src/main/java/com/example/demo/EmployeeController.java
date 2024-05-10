package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String index(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("employees", employeeService.allEmployees());
        return "index";
    }

    @PostMapping("/add") 
    public String addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") String id, Employee employee) {
        employee.setId(id);
        employeeService.editEmployee(employee, id);
        return "redirect:/";
    }
 
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
