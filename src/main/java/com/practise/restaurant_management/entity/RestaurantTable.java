package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="restaurant_tables")
@Getter
@Setter
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tableNumber;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches  branches;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "restaurantTable",fetch = FetchType.LAZY)
    private List<Order> order;

}
