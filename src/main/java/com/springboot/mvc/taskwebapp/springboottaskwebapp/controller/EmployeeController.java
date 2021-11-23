package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        List<EmployeeEntity> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("allEmployees", allEmployees);
        return "all-employees-page";
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable int id, Model model) {
        EmployeeEntity employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employee-page";
    }

    @PostMapping("/employees")
    public EmployeeEntity addNewEmployee(@RequestBody EmployeeEntity employee) {
        //Автоматическая конвертация json в Employee
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employee) {
        //Автоматическая конвертация json в Employee
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping ("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Employee with ID = " + id + " was deleted from Database.";
    }
}
