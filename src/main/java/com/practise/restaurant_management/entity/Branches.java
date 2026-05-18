package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.BranchType;
import com.practise.restaurant_management.enums.Cuisine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Branches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToOne
    private User manager;

    private String location;

    private String contactNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;

    @OneToMany(mappedBy = "branches")
    private List<Staff>  staffList;

    @OneToOne(mappedBy = "branch")
    private Menu menu;

    @OneToMany(mappedBy = "branches")
    private List<RestaurantTable> restaurantTableList;

    @OneToMany(mappedBy = "branch")
    private List<Revenue>  revenueList;

    @OneToMany(mappedBy = "branches")
    private List<Expenditure>   expenditureList;

}
