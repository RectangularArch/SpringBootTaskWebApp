package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;

import java.util.List;

/**
 *  Service class for {@link EmployeeEntity}.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface EmployeeService {
    public List<EmployeeEntity> getAllEmployees();
    public void saveEmployee(EmployeeEntity employee);
    public EmployeeEntity getEmployee(int id);
    public void deleteEmployee(int id);
}
