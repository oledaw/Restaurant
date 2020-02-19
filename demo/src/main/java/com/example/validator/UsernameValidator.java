package com.example.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(Username constraintAnnotation) {
        // TODO Auto-generated method stub
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (userRepository==null){
            return true;
        }
        return !userRepository.existsUserByUsername(value);

    }
}