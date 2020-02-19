package com.example.services;

import java.util.List;
import java.util.logging.Logger;

import com.example.domain.Restaurant;
import com.example.domain.User;
import com.example.repos.RestaurantRepository;


import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImp implements RestaurantService {

    private static Logger LOGGER = Logger.getLogger(RestaurantServiceImp.class.getName());

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImp(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void save(Restaurant newRestaurant) {
        LOGGER.info("User: " + newRestaurant.getName() + " was created. Managers are: " + newRestaurant.getUsers());
        restaurantRepository.save(newRestaurant);
    }

    @Override
    public Restaurant findByEmail(String email) {
        LOGGER.info("Found: " + email + " in Restaurant Repo.");
        return restaurantRepository.findByEmail(email);
    }

    @Override
    public List<Restaurant> findAll() {
        LOGGER.info("FindAll");
        return restaurantRepository.findAll();
    }

    @Override
    public void addManager(Restaurant restaurant, User manager) {
        LOGGER.info("Manager: " + manager.getUsername() + " was appended to " + restaurant.getName());
		restaurant.getUsers().add(manager);
		save(restaurant);
    }
    
}