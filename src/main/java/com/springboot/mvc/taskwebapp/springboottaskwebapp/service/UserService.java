package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *  Service class for {@link UserEntity},
 *  implements {@link UserDetailsService} interface.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface UserService extends UserDetailsService {
    public void saveUser(UserEntity user);
    public UserEntity findByUsername(String username);
    public UserEntity findById(int id);
    public void deleteUser(int id);

    public UserEntity getApplicationUser(int id);
}
