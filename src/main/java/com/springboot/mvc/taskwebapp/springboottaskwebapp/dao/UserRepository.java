package com.springboot.mvc.taskwebapp.springboottaskwebapp.dao;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *  Data Access Object interface for User Entity.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findUserEntityByUsername(String username);
    public UserEntity findByUsername(String username);
    public UserEntity findById(int id);
}
