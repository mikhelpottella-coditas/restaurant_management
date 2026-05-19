package com.practise.restaurant_management.controller.staff;

import com.practise.restaurant_management.dto.request.OrderItemRequestDto;
import com.practise.restaurant_management.dto.request.OrderRequestDto;
import com.practise.restaurant_management.dto.response.MenuResponseDto;
import com.practise.restaurant_management.dto.response.OrderResponseDto;
import com.practise.restaurant_management.service.MenuService;
import com.practise.restaurant_management.service.OrderService;
import com.practise.restaurant_management.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class WaiterController {

    private static final Logger log = LogManager.getLogger(WaiterController.class);
    private final MenuService menuService;
    private final OrderService orderService;
    private final RestaurantTableService tableService;

    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menuId) {
        log.info("getMenu");
        MenuResponseDto menuResponseDto = menuService.getMenu(menuId);
        return ResponseEntity.ok().body(menuResponseDto);
    }

    @GetMapping("/{staffId}/all_orders")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@PathVariable Long staffId) {
        log.info("getAllOrders for the id {}", staffId);
        List<OrderResponseDto> orderResponseDtoList = orderService.getAllOrder(staffId);
        return ResponseEntity.ok().body(orderResponseDtoList);
    }

    @PostMapping("/{staffId}/order")
    public ResponseEntity<String> createOrder(@PathVariable Long staffId, @RequestBody OrderRequestDto orderRequestDto){
        log.info("createOrder for the id {}", staffId);
        String response  = orderService.createOrder(staffId,orderRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<String> addItem(@PathVariable Long orderId, @RequestBody OrderItemRequestDto orderItemRequestDto){
        log.info("addItem for the id {}", orderId);
        String response = orderService.addOrderItem(orderId,orderItemRequestDto);
        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/pdf/{orderId}")
    public ResponseEntity<String> getPdf(@PathVariable Long orderId){
        log.info("get Pdf for the order with id : {}",orderId);
        String response = orderService.generateBill(orderId);
        return ResponseEntity.ok().body(response);
    }

}

