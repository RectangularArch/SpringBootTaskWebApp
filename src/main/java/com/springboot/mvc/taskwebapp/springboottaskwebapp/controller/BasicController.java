package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.EmployeeService;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BasicController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

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

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {

        model.addAttribute("user", userService.findById(id));
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "edit-page";
    }

    @PatchMapping("/{id}/edit")
    public String editUser(@ModelAttribute(value = "newUser") UserEntity newUser,
                                                  BindingResult bindingResultNewUser,
                                                  @ModelAttribute(value = "newEmployee") EmployeeEntity newEmployee,
                                                  BindingResult bindingResultNewEmployee) {
        //userValidator.validate(newUser, bindingResult);

        if (bindingResultNewUser.hasErrors() || bindingResultNewEmployee.hasErrors()) {
            return "redirect:/{id}/edit";
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
}
