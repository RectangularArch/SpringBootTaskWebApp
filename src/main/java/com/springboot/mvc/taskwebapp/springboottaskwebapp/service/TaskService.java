package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.TaskEntity;

import java.util.List;

/**
 *  Service class for {@link TaskEntity}.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface TaskService {
    public List<TaskEntity> getAllTasks();
    public void saveTask(TaskEntity task);
    public TaskEntity getTask(int id);
    public void deleteTask(int id);
}