package com.springboot.mvc.taskwebapp.springboottaskwebapp.dao;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Data Access Object interface for Task Entity.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {}
