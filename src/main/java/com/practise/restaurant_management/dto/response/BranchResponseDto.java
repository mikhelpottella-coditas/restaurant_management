package com.practise.restaurant_management.dto.response;


import com.practise.restaurant_management.entity.Menu;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.enums.BranchType;
import com.practise.restaurant_management.enums.Cuisine;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public record BranchResponseDto(
        Long id,

        String restaurantName,

        String  managerName,

        String location,

        String contactNumber,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        Cuisine cuisine,

        BranchType branchType,

        List<StaffResponseDto> staffResponseDtoList,

        Menu menu

        ) {
}
