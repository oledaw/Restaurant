package com.example.services;

import java.util.Optional;

import com.example.domain.RestaurantTable;

public interface TableService{

	Optional<RestaurantTable> findById(Long restaurantId);
    
}