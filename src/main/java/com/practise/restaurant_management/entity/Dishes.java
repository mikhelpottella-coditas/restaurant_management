package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.Cuisine;
import com.practise.restaurant_management.enums.DishCategory;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.internal.build.AllowNonPortable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "dishes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToMany(mappedBy = "dishes",cascade = CascadeType.ALL)
    private List<DishImage> dishImageList;

    @OneToOne(mappedBy = "dishes")
    private OrderItem orderItem;


    public void addDishImage(DishImage dishImage){
        if(dishImageList == null) dishImageList = new ArrayList<>();
        dishImageList.add(dishImage);
        dishImage.setDishes(this);
    }

}
