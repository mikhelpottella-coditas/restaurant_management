package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private String preferences;

    private Double totalPrice;

    private OrderStatus orderStatus;

    @OneToOne
    @JoinColumn(name = "dishes_id")
    private Dishes dishes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;




}
