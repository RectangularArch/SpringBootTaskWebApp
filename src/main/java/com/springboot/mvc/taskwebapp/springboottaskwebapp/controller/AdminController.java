package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

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
