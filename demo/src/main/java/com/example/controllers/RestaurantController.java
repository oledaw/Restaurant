package com.example.controllers;

import java.util.List;

import com.example.domain.Restaurant;
import com.example.dto.RestaurantLite;
import com.example.dto.RestaurantManager;
import com.example.services.RestaurantService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestaurantController.BASE_URL)
public class RestaurantController {

    final static String BASE_URL = "/api/v1";

    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = "/restaurants")
    public List<RestaurantLite> getAllRestaurant() {
        return restaurantService.findAllRestaurantsLite(RestaurantLite.class);
    }

    @PostMapping (value = "/restaurant/register")
    public void createRestaurant(@RequestBody Restaurant restaurant){
        restaurantService.save(restaurant);
    }

    @PostMapping (value = "/restaurant/addManager")
    public void addManagerToResturant(@RequestBody RestaurantManager restaurantManager){

    }

}