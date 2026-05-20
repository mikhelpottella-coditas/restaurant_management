package com.practise.restaurant_management.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record RegisterRequestDto(
        @NotBlank(message = "name is missing")
        String firstName,
        String lastName,
        @NotBlank(message = "password is missing")
        @Size(min = 6, max = 12, message = "pass word should bw in the range of 6 to 12 letters")
        String password,
        @Email(message = "invalid E-mail")
        @NotBlank(message = "email is compulsory")
        String email,
        @Size(min = 10, max = 10, message = "the phone number is invalid")
        @NotBlank
        String phoneNumber,
        String image


) {
}
