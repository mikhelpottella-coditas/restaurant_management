package com.practise.restaurant_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record SendOwnerInviteRequestDto(
        @NonNull
        @Email
        String sentTo,

        @NotBlank
        String message
) {
}
