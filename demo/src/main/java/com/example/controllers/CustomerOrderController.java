package com.example.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.NameNotFoundException;

import com.example.domain.Product;
import com.example.domain.Restaurant;
import com.example.domain.RestaurantTable;
import com.example.dto.CustomerOrderLite;
import com.example.repos.TableRepository;
import com.example.services.CustomerOrderService;
import com.example.services.RestaurantService;
import com.example.services.TableService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping(UserController.BASE_URL)
public class CustomerOrderController {
    final static String BASE_URL = "/api/v1";
    CustomerOrderService customerOrderService;
    TableService tableService;
    RestaurantService restaurantService;

    public CustomerOrderController(CustomerOrderService customerOrderService, TableService tableService) {
        this.customerOrderService = customerOrderService;
        this.tableService = tableService;
    } 

    @GetMapping(value = "/orders")
     List<CustomerOrderLite> getAllOrders() {
        return customerOrderService.findBy(CustomerOrderLite.class);
    }

    @GetMapping(value = "/menu")
    List<Product> getMenu(@RequestParam long id) throws NotFoundException {
        RestaurantTable restaurantTable = tableService.findById(id).orElseThrow(()->new NotFoundException("NIE MA TAKIEGO STOLIKA"));
        restaurantTable.setOccupied(true);
        Restaurant restaurant = restaurantTable.getRestaurant();
        tableService.save(restaurantTable);
        return restaurant.getProducts();
    }
    
    @PostMapping(value = "/release")
    ResponseEntity releaseTable(@RequestParam long id) throws NotFoundException {
        return tableService.releseTable(id);
    }        

        
        
    


    


}