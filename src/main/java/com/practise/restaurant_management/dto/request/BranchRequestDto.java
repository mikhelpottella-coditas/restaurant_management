package com.practise.restaurant_management.dto.request;


import com.practise.restaurant_management.dto.response.StaffResponseDto;
import com.practise.restaurant_management.entity.Menu;
import com.practise.restaurant_management.enums.BranchType;
import com.practise.restaurant_management.enums.Cuisine;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record BranchRequestDto(

        @NotNull(message = "restaurantId cannot be null")
       Long restaurantId,


        Long managerId,

        @NotNull(message = "location cannot be null")
        String location,

        @NotNull
        @Size(min = 10, max = 10,message = "contact number should be of 10 digits")
        String contactNumber,

        @NotNull(message = "cuisine must be mentioned")
        Cuisine cuisine,

        @NotNull(message = "branch type must be mentioned")
        BranchType branchType

        ) {
}
