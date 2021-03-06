package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.EmployeeService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.UserService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  Controller for Registration, Login and Index pages.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Controller
public class BasicController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/")
    public String getIndex() {
        return "login-page";
    }

    @GetMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("user", new UserEntity());
        model.addAttribute("employee", new EmployeeEntity());
        return "register-page";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute(value = "user") @Valid UserEntity newUser,
                                  BindingResult bindingResultNewUser,
                                  @ModelAttribute(value = "employee") @Valid EmployeeEntity newEmployee,
                                  BindingResult bindingResultNewEmployee) {
        userValidator.validate(newUser, bindingResultNewUser);

        if (bindingResultNewUser.hasErrors() || bindingResultNewEmployee.hasErrors()) {
            return "register-page";
        }

        newUser.setEmployee(newEmployee);
        employeeService.saveEmployee(newEmployee);
        userService.saveUser(newUser);

        return "redirect:/employees";
    }

    @GetMapping("/login")
    public String login() {
        return "login-page";
    }
}
