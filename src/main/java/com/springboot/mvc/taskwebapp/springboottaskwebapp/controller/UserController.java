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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/")
    public String getIndex() {
        return "index-page";
    }

    @GetMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("newUser", new UserEntity());
        model.addAttribute("newEmployee", new EmployeeEntity());

        return "register-page";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute(value = "newUser") UserEntity newUser, BindingResult bindingResultNewUser, @ModelAttribute(value = "newEmployee") EmployeeEntity newEmployee, BindingResult bindingResultNewEmployee) {
        //userValidator.validate(newUser, bindingResult);

        if (bindingResultNewUser.hasErrors() || bindingResultNewEmployee.hasErrors()) {
            return "redirect:/register";
        }

        newUser.setEmployee(newEmployee);

        employeeService.saveEmployee(newEmployee);
        userService.save(newUser);

        //securityService.autoLogin(newUser.getUsername(), newUser.getPasswordConfirmation());

        return "redirect:index-view";
    }

    @GetMapping("/login")
    public String login(Model model, String err, String logout) {
        if (err != null) {
            model.addAttribute("err", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login-page";
    }

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

    @GetMapping("/admin")
    public String getAdmin(Model model, @Autowired Principal principal) {
        UserEntity user = (UserEntity) userService.loadUserByUsername(principal.getName());

        if (!user.getAuthorities().equals(userService.getApplicationUser(1).getAuthorities())) {
            return "redirect:/employee";
        }

        EmployeeEntity employee = user.getEmployee();
        model.addAttribute("employee", employee);

        return "admin-page";
    }
}
