package com.springboot.mvc.taskwebapp.springboottaskwebapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Object that represents a Task.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "header")
    @NotBlank(message = "{Required}")
    @Size(min = 4, max = 32, message = "{Size.taskEntity.name}")
    private String head;

    @Column(name = "body")
    @NotBlank(message = "{Required}")
    @Size(min = 4, max = 250, message = "{Size.taskEntity.description}")
    private String body;

    @Column(name = "priority")
    private String priority;
    @Column(name = "status")
    private String status;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    public TaskEntity() {}

    public TaskEntity(String head,
                      String body,
                      String priority,
                      String status,
                      EmployeeEntity employee) {
        this.head = head;
        this.body = body;
        this.priority = priority;
        this.status = status;
        this.employee = employee;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
}
