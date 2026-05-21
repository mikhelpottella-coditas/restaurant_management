package com.practise.restaurant_management.controller.staff;

import com.practise.restaurant_management.dto.response.DishResponseDto;
import com.practise.restaurant_management.dto.response.OrderItemResponseDto;
import com.practise.restaurant_management.enums.OrderStatus;
import com.practise.restaurant_management.service.DishService;
import com.practise.restaurant_management.service.OrderItemService;
import com.practise.restaurant_management.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kitchen/orders")
public class CookingStaffController {

    private final OrderService orderService;
    private final DishService dishService;
    private final OrderItemService orderItemService;

    @GetMapping("/{branchId}")
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItems(@PathVariable Long branchId,
                                                                       @RequestParam(defaultValue = "0") Integer page,
                                                                       @RequestParam(defaultValue = "5") Integer size,
                                                                       @RequestParam(defaultValue = "id") String sortBy,
                                                                       @RequestParam(defaultValue = "true") Boolean ascending) {
        log.info("getAllOrderItems");
        List<OrderItemResponseDto> orderItemResponseDtos = orderService.getAllOrderItems(branchId,page,size,sortBy,ascending);
        return ResponseEntity.ok(orderItemResponseDtos);
    }

    @GetMapping("/{menuId}/all_dishes")
    public ResponseEntity<List<DishResponseDto>>  getAllDishes(@PathVariable Long menuId,
                                                               @RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "5") Integer size,
                                                               @RequestParam(defaultValue = "id") String sortBy,
                                                               @RequestParam(defaultValue = "true") Boolean ascending) {
        log.info("getAllDishes");
        List<DishResponseDto>  dishResponseDtos = dishService.getAllDishes(menuId,page,size,sortBy,ascending);
        return ResponseEntity.ok(dishResponseDtos);
    }

    @GetMapping("/dishes/{dishId}")
    public ResponseEntity<DishResponseDto> getDishes(@PathVariable Long dishId) {
        log.info("getDishes by id : {}",dishId);
        DishResponseDto dto = dishService.getDishById(dishId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/dishes/updateStatus/{dishId}")
    public ResponseEntity<String> updateStatus(@PathVariable Long dishId, @RequestParam Boolean status) {
        log.info("updateStatus by id : {}",dishId);
        String response = dishService.updateDishStatus(dishId,status);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/orderItem/updateStatus/{dishId}")
    public ResponseEntity<String> updateStatus(@PathVariable Long orderItemId, @RequestParam OrderStatus status) {
        log.info("updateStatus by id : {}",orderItemId);
        String response = orderItemService.updateDishStatus(orderItemId,status);
        return ResponseEntity.ok(response);
    }


}
