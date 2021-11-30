package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.dao.EmployeeRepository;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity getEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
