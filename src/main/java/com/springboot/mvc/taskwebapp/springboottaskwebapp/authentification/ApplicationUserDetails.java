package com.springboot.mvc.taskwebapp.springboottaskwebapp.authentification;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ApplicationUserDetails/* implements UserDetails */{
//    private String username;
//    private String password;
//    private boolean isAccountNonExpired;
//    private boolean isAccountNonLocked;
//    private boolean isCredentialsNonExpired;
//    private boolean isEnabled;
//    private Set<? extends GrantedAuthority> grantedAuthorities;
//
//    public ApplicationUserDetails() {}
//
//    public ApplicationUserDetails(UserEntity user, Set<GrantedAuthority> grantedAuthorities) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        this.isAccountNonExpired = user.isAccountNonExpired();
//        this.isAccountNonLocked = user.isAccountNonLocked();
//        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
//        this.isEnabled = user.isEnabled();
//        this.grantedAuthorities = grantedAuthorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return grantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
}
