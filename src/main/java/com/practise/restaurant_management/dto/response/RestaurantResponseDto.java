package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public record RestaurantResponseDto(
        Long id,

        String name,


        OwnerProfileDto owner,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        List<BranchResponseDto> branchResponseDtoList
) {
}
