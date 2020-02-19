package com.example.repos;

import com.example.domain.RestaurantTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    
    
}