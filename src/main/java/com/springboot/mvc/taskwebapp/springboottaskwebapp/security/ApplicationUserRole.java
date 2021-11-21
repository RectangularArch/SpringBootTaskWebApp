package com.springboot.mvc.taskwebapp.springboottaskwebapp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.springboot.mvc.taskwebapp.springboottaskwebapp.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(
            USER_READ,
            USER_WRITE,
            EMPLOYEE_READ,
            EMPLOYEE_WRITE,
            TASK_READ,
            TASK_WRITE)),
    TEAMLEAD(Sets.newHashSet(
            EMPLOYEE_READ,
            EMPLOYEE_WRITE,
            TASK_READ,
            TASK_WRITE)),
    EMPLOYEE(Sets.newHashSet(
            TASK_READ,
            TASK_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public  Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
