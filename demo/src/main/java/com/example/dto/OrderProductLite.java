package com.example.dto;

import com.example.domain.Product;


public interface OrderProductLite {
 

    long getId();
    Product getProduct();
    int getQuantity();



}