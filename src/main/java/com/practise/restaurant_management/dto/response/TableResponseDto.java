package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Staff;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record TableResponseDto(
        Long id,

        Integer tableNumber,

        Integer capacity,

        Long branchesId,

        Long staffId,


        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
