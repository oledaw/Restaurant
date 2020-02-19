package com.example.services;

import java.util.Optional;

import com.example.domain.RestaurantTable;
import com.example.repos.TableRepository;

import org.springframework.stereotype.Service;

@Service
public class TableServiceImp implements TableService {

    TableRepository tableRepository;

    public TableServiceImp(TableRepository TableRepository) {
        this.tableRepository = TableRepository;
    }

    @Override
    public Optional<RestaurantTable> findById(Long restaurantId) {
        return tableRepository.findById(restaurantId);
    }

	
    
}