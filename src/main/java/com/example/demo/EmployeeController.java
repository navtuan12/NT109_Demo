package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // edit employee
    @PutMapping("/{id}")
    public ResponseEntity<String> editEmployee(@PathVariable(value = "id") String id,
            @RequestBody Map<String, String> payload) {
        employeeService.editEmployee(payload.get("id"),
                payload.get("name"),
                payload.get("age"),
                payload.get("salary"), id);
        return new ResponseEntity<String>("Employee updated successfully", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }
}
