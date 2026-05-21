package com.practise.restaurant_management.dto.request;


import jakarta.validation.constraints.NotNull;

public record OrderItemRequestDto(

        @NotNull
        Integer quantity,

        @NotNull
        Long DishId,

        @NotNull
        String preferences


) {
}
