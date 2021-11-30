package com.springboot.mvc.taskwebapp.springboottaskwebapp.dao;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Data Access Object interface for Role Entity.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {}
