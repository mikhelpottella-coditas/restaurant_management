package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "menu")
    private List<Dishes> dishes;

    @OneToOne
    private Branches branch;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addDishes(Dishes dish) {
        if(dishes == null) dishes = new ArrayList<>();
        dishes.add(dish);
        dish.setMenu(this);
    }

}
