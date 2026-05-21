package com.practise.restaurant_management.dto.response;

import com.practise.restaurant_management.entity.OrderItem;
import com.practise.restaurant_management.entity.RestaurantTable;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,

        LocalDateTime orderedAt,

        Long staffId,


        Long restaurantTableId,


        String customerName,

        String customerNumber,

        Double taxableAmount,

        Double discountAmount,

        Double finalPrice,

        PaymentStatus paymentStatus,

        List<OrderItemResponseDto> orderItemResponseDtoList
) {
}
