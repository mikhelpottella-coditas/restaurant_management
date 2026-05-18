package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private User managerUser;

    @ManyToOne
    private Branches branches;


    private Double salary;

    @Column(name = "availability")
    private Boolean availability;

    private LocalDateTime joinedAt;

    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "staff")
    private Order order;

    @OneToMany(mappedBy = "staff")
    private List<RestaurantTable>  restaurantTable;



}
