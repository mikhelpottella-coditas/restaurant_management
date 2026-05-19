package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.RegisterRestaurantDto;
import com.practise.restaurant_management.dto.response.BranchResponseDto;
import com.practise.restaurant_management.dto.response.OwnerProfileDto;
import com.practise.restaurant_management.dto.response.RestaurantResponseDto;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.RestaurantRepo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private static final Logger log = LogManager.getLogger(RestaurantService.class);
    private final RestaurantRepo restaurantRepo;
    private final UserService userService;
    private final OwnerService ownerService;

    public String registerRestaurant(RegisterRestaurantDto registerRestaurantDto) {
        Restaurant restaurant = new Restaurant();
        User owner = userService.findById(registerRestaurantDto.ownerId());
        restaurant.setName(registerRestaurantDto.name());
        restaurant.setOwner(owner);
        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setUpdatedAt(LocalDateTime.now());

        restaurantRepo.save(restaurant);
        log.info("restaurant saved : {}", restaurant.getName());
        return "new restaurant registered successfully";

    }

    public String updateRestaurant(RegisterRestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        log.info("update restaurant : {}", restaurantDto);
        User owner = null;
        if (restaurantDto.ownerId()!=null) owner = userService.findById(restaurantDto.ownerId());
        if(restaurantDto.name()!=null)restaurant.setName(restaurantDto.name());
        if(owner!=null) restaurant.setOwner(owner);

        restaurant.setUpdatedAt(LocalDateTime.now());
        restaurantRepo.save(restaurant);
        log.info("restaurant updated : {}", restaurant.getName());
        return "restaurant updated successfully";

    }

    public Restaurant getById(Long id){
        return restaurantRepo.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "restaurant not found"));
    }

    public RestaurantResponseDto getResponseById(Long id) {
        log.info("fetching restaurant with id : {}", id);
        Restaurant restaurant =  restaurantRepo.findById(id).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "restaurant not found"));
        log.info("restaurant found : {}", restaurant.getName());
        OwnerProfileDto ownerProfileDto = ownerService.getOwnerProfile(restaurant.getOwner().getId());
        log.info("owner profile found for restaurant : {}", restaurant.getName());
        log.info("branches data fetching for restaurant : {}", restaurant.getName());
        List<BranchResponseDto> branchResponseDtoList = restaurant.getBranches().stream().map(branch -> new BranchResponseDto(branch.getId(), restaurant.getName(), branch.getManager().getFirstName(), branch.getLocation(), branch.getContactNumber(), branch.getCreatedAt(), branch.getUpdatedAt(), branch.getCuisine(), branch.getBranchType(), null, null)).toList();

        return new RestaurantResponseDto(restaurant.getId(), restaurant.getName(),ownerProfileDto,restaurant.getCreatedAt(),restaurant.getUpdatedAt(), branchResponseDtoList);
    }


    public List<Restaurant> getAll(){
        return restaurantRepo.findAll();
    }

    public List<RestaurantResponseDto> getAllRestaurants() {

        List<Restaurant> restaurantList= getAll();
        List<RestaurantResponseDto> restaurantResponseDtoList = new ArrayList<>();
        restaurantList.forEach(restaurant -> {
            OwnerProfileDto ownerProfileDto = ownerService.getOwnerProfile(restaurant.getOwner().getId());
            restaurantResponseDtoList.add( new RestaurantResponseDto(restaurant.getId(), restaurant.getName(),ownerProfileDto , restaurant.getCreatedAt(), restaurant.getUpdatedAt(), null));
        });

        log.info("restaurant found : {}", restaurantResponseDtoList);
        return restaurantResponseDtoList;

    }
}
