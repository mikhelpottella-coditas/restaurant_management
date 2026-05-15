package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.Cuisine;
import com.practise.restaurant_management.enums.DishCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;

    private String description;

    private Integer calories;

    private String ingredient;

    private DishCategory category;

    private Cuisine cuisine;

    private Boolean isAvailable;

    private Double price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "dishes")
    private List<DishImage> dishImage;

    @OneToOne(mappedBy = "dishes")
    private OrderItem orderItem;


}
