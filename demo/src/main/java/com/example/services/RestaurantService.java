package com.example.services;

import java.util.List;

import com.example.domain.Restaurant;
import com.example.domain.User;

public interface RestaurantService {

	void save(Restaurant newRestaurant);

	Restaurant findByEmail(String email);

	List<Restaurant> findAll();

	<T> List <T> findAllRestaurantsLite(Class <T> type);

	void addManager (Restaurant restaurant, User manager);

}
