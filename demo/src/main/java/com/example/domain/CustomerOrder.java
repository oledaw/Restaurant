package com.example.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "customerOrder",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<OrderProduct> orderProducts = new HashSet<>();

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
        this.restaurant = table.getRestaurant();
    }

    public void addToTotal(){
        this.total+=getOrderProducts().iterator().next().getProduct().getPrice()*getOrderProducts().iterator().next().getQuantity();
    }

    public void checked(){
        setStatus("CHECKED");
        
    }






}