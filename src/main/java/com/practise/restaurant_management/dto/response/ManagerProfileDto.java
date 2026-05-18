package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public record ManagerProfileDto(
        Long id,

        String firstName,

        String lastName,

        String email,
        String phoneNumber,

        Role role,

        String image,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
