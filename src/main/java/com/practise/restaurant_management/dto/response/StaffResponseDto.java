package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StaffResponseDto(
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

        Long managerId,

        Long BranchId,

        Double salary
) {
}
