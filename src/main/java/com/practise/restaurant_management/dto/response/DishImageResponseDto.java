package com.practise.restaurant_management.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public record DishImageResponseDto(
        Long id,

         String image,

         String referenceText
) {
}
