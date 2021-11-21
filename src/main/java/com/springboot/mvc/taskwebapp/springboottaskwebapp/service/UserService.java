package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
//    public List<UserEntity> getAllApplicationUsers();
//    public void saveApplicationUser(UserEntity applicationUser);
    public UserEntity getApplicationUser(int id);
//    public void deleteApplicationUser(int id);

    public void save(UserEntity user);
    public UserEntity findByUsername(String username);
}
