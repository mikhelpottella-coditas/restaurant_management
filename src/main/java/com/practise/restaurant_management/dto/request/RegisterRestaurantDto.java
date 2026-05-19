package com.practise.restaurant_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

public record RegisterRestaurantDto(

        @NotBlank
        String name,

        @NotNull
        Long ownerId

) {

}
