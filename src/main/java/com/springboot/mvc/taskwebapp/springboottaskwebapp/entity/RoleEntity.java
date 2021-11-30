package com.springboot.mvc.taskwebapp.springboottaskwebapp.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  Object that represents a Role.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;

    @Column(name = "role")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions;

    public RoleEntity() {}

    public RoleEntity(String role,
                      Set<UserEntity> users,
                      Set<PermissionEntity> permissions) {
        this.role = role;
        this.users = users;
        this.permissions = permissions;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public void addPermissionToRole(PermissionEntity permission) {
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        permissions.add(permission);
    }

    public void addUserToRole(UserEntity user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
    }
}
