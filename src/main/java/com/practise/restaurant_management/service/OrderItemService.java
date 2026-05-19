package com.practise.restaurant_management.service;

import com.practise.restaurant_management.entity.Dishes;
import com.practise.restaurant_management.entity.OrderItem;
import com.practise.restaurant_management.enums.OrderStatus;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.OrderItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    public OrderItem getById(Long id) {
        return  orderItemRepo.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "order item not found with id : " + id));
    }

    public String updateDishStatus(Long orderItemId, OrderStatus status) {

        OrderItem orderItem = getById(orderItemId);

        orderItem.setOrderStatus(status);

    }
}
