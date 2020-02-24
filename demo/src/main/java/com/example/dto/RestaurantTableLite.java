package com.example.dto;

import java.time.LocalDateTime;

public interface RestaurantTableLite {
    long getId();
    int getSits();
    boolean isOccupied();
    LocalDateTime getLastTimeStamp();
    
}