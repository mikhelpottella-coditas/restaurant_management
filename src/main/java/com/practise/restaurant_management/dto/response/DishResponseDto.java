package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.enums.Cuisine;
import com.practise.restaurant_management.enums.DishCategory;

import java.time.LocalDateTime;
import java.util.List;

public record DishResponseDto(
         Long id,

         String  name,

         String description,

         Integer calories,

         String ingredient,

         DishCategory category,

         Cuisine cuisine,

         Boolean isAvailable,

         Double price,

         List<DishImageResponseDto> imageList,

         Long menuId,

         LocalDateTime createdAt,
         LocalDateTime updatedAt
)  {
}
