package com.springboot.mvc.taskwebapp.springboottaskwebapp.dao;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Data Access Object interface for Employee Entity.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
