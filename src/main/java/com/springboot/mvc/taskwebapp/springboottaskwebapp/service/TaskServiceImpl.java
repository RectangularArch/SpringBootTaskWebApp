package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.dao.TaskRepository;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void saveTask(TaskEntity task) {
        taskRepository.save(task);
    }

    @Override
    public TaskEntity getTask(int id) {
        TaskEntity task = null;
        Optional<TaskEntity> optional = taskRepository.findById(id);
        if (optional.isPresent()) {
            task = optional.get();
        }
        return task;
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
