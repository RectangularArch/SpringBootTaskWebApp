package com.springboot.mvc.taskwebapp.springboottaskwebapp.security;

public enum ApplicationUserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    TASK_READ("employee:read"),
    TASK_WRITE("employee:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
