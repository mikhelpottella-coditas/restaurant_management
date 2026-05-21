package com.practise.restaurant_management.dto.request;


import com.practise.restaurant_management.enums.Cuisine;
import com.practise.restaurant_management.enums.DishCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateDishDto (

        @NotBlank(message = "dish must have a name")
        String  name,


        String description,


        Integer calories,


        String ingredient,

        @NotNull
        DishCategory category,

        @NotNull
        Cuisine cuisine,


        Boolean isAvailable,

        @NotNull
        Double price,

        List<String> imageList,

        @NotNull
        Long menuId


){
}
