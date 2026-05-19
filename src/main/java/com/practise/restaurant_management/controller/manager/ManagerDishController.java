package com.practise.restaurant_management.controller.manager;

import com.practise.restaurant_management.dto.request.CreateDishDto;
import com.practise.restaurant_management.dto.response.DishResponseDto;
import com.practise.restaurant_management.entity.Dishes;
import com.practise.restaurant_management.service.DishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/manager/dishes")
public class ManagerDishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<String> createDish(@RequestBody CreateDishDto createDishDto) {
        log.info("createDish with name : {}", createDishDto.name());
        String response = dishService.createDish(createDishDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/branch/{menuId}")
    public ResponseEntity<List<DishResponseDto>> getAllDishes(@PathVariable Long menuId) {
        log.info("getAllDishes");
        List<DishResponseDto> responseDtoList = dishService.getAllDishes(menuId);
        return ResponseEntity.ok(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable Long id) {
        log.info("getDishById : {}", id);
        DishResponseDto responseDto = dishService.getDishById(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDish(@PathVariable Long id, @RequestBody CreateDishDto createDishDto) {
        log.info("updateDish with name : {}", createDishDto.name());
        String response = dishService.updateDish(id,createDishDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
        log.info("deleteDish : {}", id);
        String response = dishService.deleteDish(id);
        return ResponseEntity.ok(response);
    }

}
