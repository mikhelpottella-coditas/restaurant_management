package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record OwnerProfileDto(
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
