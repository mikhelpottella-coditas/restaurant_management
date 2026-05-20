package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.CreateDishDto;
import com.practise.restaurant_management.dto.response.DishImageResponseDto;
import com.practise.restaurant_management.dto.response.DishResponseDto;
import com.practise.restaurant_management.entity.DishImage;
import com.practise.restaurant_management.entity.Dishes;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.DishRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishService {

    private final DishRepo dishRepo;
    private final MenuService menuService;

    public String createDish(CreateDishDto createDishDto) {

        Dishes dishes = Dishes.builder()
                .name(createDishDto.name())
                .price(createDishDto.price())
                .category(createDishDto.category())
                .calories(createDishDto.calories())
                .cuisine(createDishDto.cuisine())
                .description(createDishDto.description())
                .ingredient(createDishDto.ingredient())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .menu(menuService.getById(createDishDto.menuId()))
                .build();

        createDishDto.imageList().forEach(image->{
            DishImage dishImage = new  DishImage();
            dishImage.setImage(image);
            dishes.addDishImage(dishImage);
        });



        dishRepo.save(dishes);
        log.info("createDish with name : {}",dishes.getName());
        return "new dish is created with name : "+dishes.getName();
    }

    public List<Dishes> getAll(){
        return dishRepo.findAll();
    }

    public List<DishResponseDto> getAllDishes(Long menuId, Integer page, Integer size, String sortBy, Boolean ascending) {

        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<Dishes> dishesList = dishRepo.findAllByMenuId(menuId,pageable).getContent();

        List<DishResponseDto> responseDtoList = new ArrayList<>();
        dishesList.forEach(dish ->
        {
            List<DishImageResponseDto> imageResponseDtoList = dish.getDishImageList().stream().map(image -> new DishImageResponseDto(image.getId(), image.getImage(), image.getReferenceText())).toList();
                responseDtoList.add(new DishResponseDto(dish.getId(), dish.getName(), dish.getDescription(), dish.getCalories(), dish.getIngredient(),dish.getCategory(),dish.getCuisine(),dish.getIsAvailable(),dish.getPrice(),imageResponseDtoList,dish.getMenu().getId(),dish.getCreatedAt(),dish.getUpdatedAt()));
        });

        log.info("getAllDishes : {}",responseDtoList);
        return responseDtoList;
    }


    public Dishes getById(Long id){
        return dishRepo.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "dish not found with the given id"));
    }

    public DishResponseDto getDishById(Long id) {
        Dishes dishes = getById(id);
        List<DishImageResponseDto> imageResponseDtoList = dishes.getDishImageList().stream().map(image -> new DishImageResponseDto(image.getId(), image.getImage(), image.getReferenceText())).toList();
        log.info("get the data of a dish with id : {}",id);
        return new DishResponseDto(dishes.getId(), dishes.getName(), dishes.getDescription(), dishes.getCalories(), dishes.getIngredient(), dishes.getCategory(), dishes.getCuisine(), dishes.getIsAvailable(), dishes.getPrice(), imageResponseDtoList, dishes.getMenu().getId(),dishes.getCreatedAt(), dishes.getUpdatedAt());
    }


    public String updateDish(Long id, CreateDishDto createDishDto) {

        Dishes dishes = getById(id);

        if(createDishDto.name() !=null) dishes.setName(createDishDto.name());
        if(createDishDto.category() !=null) dishes.setCategory(createDishDto.category());
        if(createDishDto.isAvailable()!=null) dishes.setIsAvailable(createDishDto.isAvailable());
        if(createDishDto.calories()!=null) dishes.setCalories(createDishDto.calories());
        if(createDishDto.ingredient()!=null) dishes.setIngredient(createDishDto.ingredient());
        if(createDishDto.description()!=null) dishes.setDescription(createDishDto.description());
        if(createDishDto.price()!=null) dishes.setPrice(createDishDto.price());
        if(createDishDto.menuId()!=null) dishes.setMenu(menuService.getById(createDishDto.menuId()));
        if(createDishDto.cuisine()!=null) dishes.setCuisine(createDishDto.cuisine());

        dishRepo.save(dishes);
        log.info("updated the dish with name : {}",dishes.getName());
        return "updated dish with the name : "+dishes.getName();
    }

    public String deleteDish(Long id) {
        Dishes dishes = getById(id);
        dishRepo.delete(dishes);
        log.info("dish deleted with the id : {}",id);
        return "dish deleted with the id"+id;
    }

    public String updateDishStatus(Long dishId, Boolean status) {
        Dishes dishes = getById(dishId);
        dishes.setIsAvailable(status);
        dishRepo.save(dishes);
        log.info("updated the dish with status : {}",dishes.getIsAvailable());
        return "updated dish with status : "+dishes.getIsAvailable();
    }
}
