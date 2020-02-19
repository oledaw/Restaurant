package com.example.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class RestaurantManager{
    @Getter
    @Setter
    String restaurantEmail;
    @Getter
    @Setter
    String managerEmail;
}