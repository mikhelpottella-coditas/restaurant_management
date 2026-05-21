package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record ManagerProfileDto(
        Long id,

        @NotBlank
        String firstName,

        String lastName,

        @NotBlank
        @Email(message = "invalid email address")
        String email,

        @NotBlank
        @Size(min = 10, max = 10,message = "invalid mobile number")
        String phoneNumber,

        Role role,

        String image,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
