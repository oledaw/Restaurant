package com.example.services;

import java.util.Optional;

import com.example.domain.RestaurantTable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface TableService{

	Optional<RestaurantTable> findById(Long restaurantId);

	ResponseEntity save(RestaurantTable restaurantTable);

	ResponseEntity releseTable(@RequestParam long id);
    
}