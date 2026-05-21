package com.practise.restaurant_management.dto.request;

import com.practise.restaurant_management.entity.Branches;
import jakarta.validation.constraints.NotNull;

public record MenuRequestDto(
        @NotNull
        Long branchId
) {
}
