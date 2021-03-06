package com.springboot.mvc.taskwebapp.springboottaskwebapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 *  Object that represents an Employee.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "firstname")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.firstname}")
    private String firstname;

    @Column(name = "surname")
    @NotBlank(message = "{Required}")
    @Size(min = 2, message = "{Size.employeeEntity.surname}")
    private String surname;

    @Column(name = "middlename")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.middlename}")
    private String middlename;

    @Column(name = "department")
    @NotBlank(message = "{Required}")
    @Size(min = 2, message = "{Size.employeeEntity.department}")
    private String department;

    @Column(name = "position")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.employeeEntity.position}")
    private String position;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<TaskEntity> tasks;

    public EmployeeEntity() {}

    public EmployeeEntity(String firstname,
                          String surname,
                          String middlename,
                          String department,
                          String position,
                          List<TaskEntity> tasks) {
        this.firstname = firstname;
        this.surname = surname;
        this.middlename = middlename;
        this.department = department;
        this.position = position;
        this.tasks = tasks;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public void addTaskToEmployee(TaskEntity task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        task.setEmployee(this);
    }
}
