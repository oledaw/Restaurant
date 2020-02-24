package com.example.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.domain.Manager;
import com.example.domain.User;
import com.example.dto.UserLite;
import com.example.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    final static String BASE_URL = "/api/v1";

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    List<UserLite> getAllUsersCustom() throws JsonProcessingException {
        return userService.findBy(UserLite.class);
    }

    @PostMapping(value = "/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@RequestBody @Valid User user) {
        userService.save(user);
    }

    @PostMapping(value = "/manager/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewManager(@RequestBody Manager manager) {
        userService.save(manager);
    }
    


}