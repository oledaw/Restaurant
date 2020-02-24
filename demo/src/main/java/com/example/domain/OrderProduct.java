package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
 
    // @EmbeddedId
    // OrderProductPK id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;
 
    @ManyToOne
    @JoinColumn(name = "product_id")
    // @MapsId("productid")
    @Getter
    @Setter
    Product product;
 
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    // @MapsId("orderid")
    @Getter
    @Setter
    CustomerOrder customerOrder;
 
    int quantity;


    public OrderProduct(Product product, CustomerOrder customerOrder, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.customerOrder = customerOrder;
    }


}