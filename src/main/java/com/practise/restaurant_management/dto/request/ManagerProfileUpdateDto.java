package com.practise.restaurant_management.dto.request;

import com.practise.restaurant_management.enums.Role;

import java.time.LocalDateTime;

public record ManagerProfileUpdateDto(
        Long id,

        String firstName,

        String lastName,

        String email,
        String phoneNumber,

        Role role,

        String image

) {
}
