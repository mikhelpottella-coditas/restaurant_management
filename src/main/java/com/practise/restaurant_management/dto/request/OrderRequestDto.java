package com.practise.restaurant_management.dto.request;

import com.practise.restaurant_management.dto.response.OrderItemResponseDto;
import com.practise.restaurant_management.enums.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRequestDto(

        @NotNull
        Long staffId,


        @NotNull
        Long restaurantTableId,

        @NotBlank
        String customerName,

        @NotBlank
        String customerNumber,

        @NotNull
        Double discountAmount,


        List<OrderItemRequestDto> orderItemRequestDtoList

) {
}
