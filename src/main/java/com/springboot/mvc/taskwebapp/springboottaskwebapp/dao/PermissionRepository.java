package com.springboot.mvc.taskwebapp.springboottaskwebapp.dao;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  Data Access Object interface for Permission Entity.
 *
 * @author Andrey Tolstopyatov
 * @version 1.0
 */

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {}
