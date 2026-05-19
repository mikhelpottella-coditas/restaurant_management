package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User managerUser;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Branches branches;


    private Double salary;

    @Column(name = "availability")
    private Boolean availability;

    private LocalDateTime joinedAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "staff",orphanRemoval = true,cascade = CascadeType.MERGE)
    private List<Order> order;

    @OneToMany(mappedBy = "staff",orphanRemoval = true,cascade = CascadeType.MERGE)
    private List<RestaurantTable>  restaurantTable;



}
