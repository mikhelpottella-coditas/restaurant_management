package com.practise.restaurant_management.controller.superAdmin;

import com.practise.restaurant_management.dto.response.RestaurantResponseDto;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/restaurants")
public class AdminRestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants(){
        log.info("getAllRestaurants");
        List<RestaurantResponseDto> responseDtoList = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long id){
        log.info("getRestaurant By Id : {}",id);
        RestaurantResponseDto restaurantResponseDto = restaurantService.getResponseById(id);
        return ResponseEntity.ok(restaurantResponseDto);
    }

}
