package com.springboot.mvc.taskwebapp.springboottaskwebapp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *  Object that represents a User.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

@Entity
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotBlank(message = "{Required}")
    @Size(min = 4, max = 32, message = "{Size.userEntity.username}")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "{Required}")
    @Size(min = 4, message = "{Size.userEntity.password}")
    private String password;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Transient
    private String passwordConfirmation;

    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    public UserEntity() {
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }

    public UserEntity(String username,
                      String password,
                      boolean isAccountNonExpired,
                      boolean isAccountNonLocked,
                      boolean isCredentialsNonExpired,
                      boolean isEnabled,
                      Set<? extends GrantedAuthority> grantedAuthorities,
                      EmployeeEntity employee) {
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.grantedAuthorities = grantedAuthorities;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }


    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public void addRoleToUser(RoleEntity role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }
}
