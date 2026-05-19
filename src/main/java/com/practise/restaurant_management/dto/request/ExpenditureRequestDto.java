package com.practise.restaurant_management.dto.request;

import com.practise.restaurant_management.entity.Branches;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record ExpenditureRequestDto(

         Double amount,

         Long branchesId
) {
}
