package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.Dishes;
import com.practise.restaurant_management.entity.Order;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public record OrderItemResponseDto(
        Long id,

        Integer quantity,

        String preferences,

        Double totalPrice,

        Long dishesId,

        Long orderId
) {
}
