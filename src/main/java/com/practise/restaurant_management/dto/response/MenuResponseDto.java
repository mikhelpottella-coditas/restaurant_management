package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;

import java.time.LocalDateTime;
import java.util.List;

public record MenuResponseDto(
         Long id,

         List<DishResponseDto> dishesResponseDtoList,

         Long branchId,

         LocalDateTime createdAt,
         LocalDateTime updatedAt
) {
}
