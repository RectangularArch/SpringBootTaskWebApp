package com.springboot.mvc.taskwebapp.springboottaskwebapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  Object that represents a Permission.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Entity
@Table(name = "permission")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "permission")
    private String permission;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public PermissionEntity() {}

    public PermissionEntity(String permission,
                            Set<RoleEntity> roles) {
        this.permission = permission;
        this.roles = roles;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public void addRoleToPermission(RoleEntity role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
