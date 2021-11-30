package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.RoleEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.TaskEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.*;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 *  Controller for {@link UserEntity}'s pages.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Controller
public class UserController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/employee")
    public String getEmployee(Model model, @Autowired Principal principal) {
        UserEntity user = (UserEntity) userService.loadUserByUsername(principal.getName());

        EmployeeEntity employee = user.getEmployee();
        model.addAttribute("employee", employee);

        return "employee-page";
    }

    @GetMapping("/employee/{id}/tasks")
    public String showAllEmployeeTasks(Model model, @PathVariable("id") int id) {
        UserEntity user = userService.findById(id);
        List<TaskEntity> allTasks = user.getEmployee().getTasks();
        model.addAttribute("allTasks", allTasks);

//        for (RoleEntity item : user.getRoles()) {
//            if (item.getRole().equals("ROLE_EMPLOYEE")) {
//                return "redirect:/employee";
//            }
//        }

        return "all-tasks-page";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("employee", employeeService.getEmployee(id));

        return "edit-user-page";
    }

    @PatchMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id,
                           @ModelAttribute(value = "user") @Valid UserEntity user,
                           BindingResult bindingResultNewUser,
                           @ModelAttribute(value = "employee") @Valid EmployeeEntity employee,
                           BindingResult bindingResultNewEmployee) {
        userValidator.validate(user, bindingResultNewUser);

        if (bindingResultNewUser.hasErrors() || bindingResultNewEmployee.hasErrors()) {
            return "edit-user-page";
        }

        user.setEmployee(employee);
        employeeService.saveEmployee(employee);
        userService.saveUser(user);

        return "redirect:/employees";
    }

    @GetMapping("/{id}/tasks")
    public String addTaskToEmployee(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", new TaskEntity());
        model.addAttribute("employee", employeeService.getEmployee(id));
        return "new-task-page";
    }

    @PostMapping("/{employeeId}/tasks")
    public String addTaskToEmployee(@ModelAttribute(value = "task") @Valid TaskEntity newTask,
                           BindingResult bindingResultNewTask,
                                    @PathVariable("employeeId") int employeeId, Model model) {
        if (bindingResultNewTask.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployee(employeeId));
            return "new-task-page";
        }

        newTask.setEmployee(employeeService.getEmployee(employeeId));
        taskService.saveTask(newTask);

        return "redirect:/employee/{employeeId}/tasks";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editEmployeeTask(Model model, @PathVariable("id") int id) {
        model.addAttribute("task", taskService.getTask(id));
        return "edit-task-page";
    }

    @PatchMapping("/tasks/{id}/edit")
    public String editEmployeeTask(@PathVariable("id") int id,
                                   @ModelAttribute(value = "task") @Valid TaskEntity newTask,
                                   BindingResult bindingResultNewTask) {
        int employeeId;
        if (bindingResultNewTask.hasErrors()) {
            return "edit-task-page";
        }
        TaskEntity task = taskService.getTask(id);
        newTask.setEmployee(task.getEmployee());
        employeeId = task.getEmployee().getId();
        taskService.saveTask(newTask);

        return "redirect:/employee/" + employeeId + "/tasks";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/employees";
    }

}
