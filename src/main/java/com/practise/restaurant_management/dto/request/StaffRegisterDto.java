package com.practise.restaurant_management.dto.request;


import com.practise.restaurant_management.enums.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDateTime;

public record StaffRegisterDto(
        @NotBlank
        String firstName,

        String lastName,

        @NotBlank
        @Email(message = "invalid email address")
        String email,

        @NotBlank
        @Size(min = 6, max =12,message = "password range should btw in the range of 6 to 12")
        String password,

        @NotBlank
       @Size(min = 10, max = 10,message = "invalid mobile number")
        String phoneNumber,

        Role role,

        String image,

        Long managerId,

        Long branchId,

        Double salary
) {
}
