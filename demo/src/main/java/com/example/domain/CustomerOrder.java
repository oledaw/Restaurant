package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinTable(
                name = "corder_product",
                joinColumns = @JoinColumn(
                        name = "corder_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "product_id", referencedColumnName = "id"))
    @Getter
    @Setter
    private List<Product> products = new ArrayList<>();
    @Getter
    @Setter
    private float total = 0;
    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    @ManyToOne
	@JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Getter
    @Setter
    private long restaurantTable_id;




    public CustomerOrder(User user, RestaurantTable table) {
        this.user = user;
        this.restaurantTable_id = table.getId();
    }

    public void addToTotal(float price){
        this.total+=price;
    }






}