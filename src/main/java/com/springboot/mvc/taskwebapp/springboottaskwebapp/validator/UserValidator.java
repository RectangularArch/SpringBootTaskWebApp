package com.springboot.mvc.taskwebapp.springboottaskwebapp.validator;

import com.springboot.mvc.taskwebapp.springboottaskwebapp.entity.UserEntity;
import com.springboot.mvc.taskwebapp.springboottaskwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username",
                    "Duplicate.userForm.username",
                    "Such username already exists.");
        }

        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation",
                    "Different.userForm.password",
                    "Password don't match.");
        }
    }
}
