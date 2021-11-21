package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.dao.RoleRepository;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.RoleEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;
import java.util.Set;

public class RoleServiceImpl implements RoleService{
//    @Autowired
//    private RoleRepository roleRepository;
//
////    @Override
////    public RoleEntity findRoleEntityByUserEntity(UserEntity userEntity) throws RoleNotFoundException {
////        Optional<RoleEntity> roleEntity = roleRepository.findRoleEntityByUserEntity(userEntity);
////            roleEntity.orElseThrow(() ->
////                    new RoleNotFoundException(String.format("Role for %s not found", userEntity.getUsername())));
////        return roleEntity.get();
////    }
//    public Set<RoleEntity> findAllByUserEntity (UserEntity userEntity) {
//        return roleRepository.findAllByUserEntity(userEntity);
//    }
}
