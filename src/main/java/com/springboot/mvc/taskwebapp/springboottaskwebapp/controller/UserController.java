package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.authentification.ApplicationUserDetails;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.EmployeeService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.SecurityService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.TaskService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.UserService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/employee")
    public String getEmployee(Model model, @Autowired Principal principal) {
        UserEntity user = (UserEntity) userService.loadUserByUsername(principal.getName());

        if (user.getAuthorities().equals(userService.getApplicationUser(1).getAuthorities())) {
            return "redirect:/admin";
        }

        EmployeeEntity employee = user.getEmployee();
        model.addAttribute("employee", employee);

        return "employee-page";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/employees";
    }
}
