package com.example.demo;

import java.util.List;

import com.example.domain.CustomerOrder;
import com.example.domain.Manager;
import com.example.domain.Product;
import com.example.domain.Restaurant;
import com.example.domain.RestaurantTable;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.domain.Role.Type;
import com.example.dto.RestaurantManager;
import com.example.repos.CustomerOrderRepository;
import com.example.repos.RoleRepository;
import com.example.repos.TableRepository;
import com.example.services.RestaurantService;
import com.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner{
    UserService userService;
    RoleRepository roleRepository;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    TableRepository tablereposiotry;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    


    public BootStrap(final UserService userService, final RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(final String... args) throws Exception {
        final Type[] type = Type.values();
        for (final Type t:type){
            roleRepository.save(new Role(t));
        }

          
    }


}