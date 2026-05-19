package com.practise.restaurant_management.controller.owner;

import com.practise.restaurant_management.dto.request.RegisterRestaurantDto;
import com.practise.restaurant_management.dto.response.RestaurantResponseDto;
import com.practise.restaurant_management.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/restaurant")
@RequiredArgsConstructor
@Slf4j
public class OwnerRestaurantController {

    private final RestaurantService restaurantService;


    @PostMapping
    public String registerRestaurant(@Valid @RequestBody RegisterRestaurantDto registerRestaurantDto) {
       log.info("new restaurant registered : {}", registerRestaurantDto.toString());
        return  restaurantService.registerRestaurant(registerRestaurantDto);
    }

    @PutMapping
    public String updateRestaurant(@RequestBody RegisterRestaurantDto restaurantDto) {
        log.info("new restaurant updated : {}", restaurantDto.toString());
        return restaurantService.updateRestaurant(restaurantDto);
    }

    @GetMapping("/{id}")
    public RestaurantResponseDto getRestaurant(@PathVariable Long id) {
        log.info("get restaurant by id : {}", id);
        return restaurantService.getResponseById(id);
    }




}
