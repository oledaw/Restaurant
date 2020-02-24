package com.example.dto;

import java.util.Set;

public interface CustomerOrderLite {
    

    long getId();
    float getTotal();
    String getStatus();
    RestaurantLite getRestaurant();
    UserLite getUser();
    long getRestaurantTable_id();
    Set<OrderProductLite> getOrderProducts();
    

}