package com.springboot.mvc.taskwebapp.springboottaskwebapp.controller;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.TaskEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showAllTasks(Model model) {
        List<TaskEntity> allTasks = taskService.getAllTasks();
        model.addAttribute("allTasks", allTasks);
        return "all-tasks-page";
    }

    @GetMapping("/tasks/{id}")
    public TaskEntity getTask(@PathVariable int id) {
        TaskEntity task = taskService.getTask(id);
        return task;
    }
    @PostMapping("/tasks")
    public TaskEntity addNewTask(@RequestBody TaskEntity task) {
        //Автоматическая конвертация json в Task
        taskService.saveTask(task);
        return task;
    }
    @PutMapping("/tasks")
    public TaskEntity updateTask(@RequestBody TaskEntity task) {
        //Автоматическая конвертация json в Task
        taskService.saveTask(task);
        return task;
    }
//    @DeleteMapping ("/tasks/{id}")
//    public String deleteTask(@PathVariable int id) {
//        taskService.deleteTask(id);
//        return "Task with ID = " + id + " was deleted from Database.";
//    }
}
