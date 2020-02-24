package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements Serializable {


    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "restaurant_managers",
                joinColumns = @JoinColumn(
                        name = "restaurant_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "user_email", referencedColumnName = "email"))
    @Getter
    @Setter
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter
    @Setter
    Set<RestaurantTable> restaurantTables;

//     @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     @Getter
//     @Setter
//     Set<CustomerOrder> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "restaurant_product",
                joinColumns = @JoinColumn(
                        name = "restaurant_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(
                        name = "product_id", referencedColumnName = "id"))
    @Getter
    @Setter
    private List<Product> products = new ArrayList<>();


    @JsonCreator
    public Restaurant( @JsonProperty("name") String name, @JsonProperty("password") String password, @JsonProperty("type") String type, 
                    @JsonProperty("email") String email) {
        this.name = name;
        this.password = password;
        this.type = type;
        this.email = email;
    }
    
}
