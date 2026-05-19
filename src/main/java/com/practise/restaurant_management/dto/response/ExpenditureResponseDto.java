package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ExpenditureResponseDto(
        Long id,

         LocalDateTime date,

         Double amount,

         Long branchesId
) {
}
