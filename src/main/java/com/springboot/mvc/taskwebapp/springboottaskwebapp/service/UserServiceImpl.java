package com.springboot.mvc.taskwebapp.springboottaskwebapp.service;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.dao.RoleRepository;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.dao.UserRepository;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.PermissionEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.RoleEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.getById(3));

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByUsername(username);
        userEntity.orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : userEntity.get().getRoles()) {
            for (PermissionEntity permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            }
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        //-----------
        userEntity.get().setAuthorities(grantedAuthorities);
        return userEntity.get();
        //-----------
//        ApplicationUserDetails applicationUserDetails
//                = new ApplicationUserDetails(userEntity.get(), grantedAuthorities);
//        return applicationUserDetails;
    }

    @Override
    public UserEntity getApplicationUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findById(int id) {
        return userRepository.findById(id);
    }
}
