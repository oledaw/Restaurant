package com.example.dto;

import java.util.Set;

public interface RestaurantLite {

        long getId();
        String getName();
        String getType();
        String getEmail();
        Set <RestaurantTableLite> getRestaurantTables();
         
}
