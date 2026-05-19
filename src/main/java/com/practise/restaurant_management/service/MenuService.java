package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.MenuRequestDto;
import com.practise.restaurant_management.dto.response.DishImageResponseDto;
import com.practise.restaurant_management.dto.response.DishResponseDto;
import com.practise.restaurant_management.dto.response.MenuResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Menu;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.MenuRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuService {

    private final MenuRepo menuRepo;
    private final BranchService branchService;

    public String createMenu(MenuRequestDto menuRequestDto) {
        Branches branches = branchService.getById(menuRequestDto.branchId());

        Menu menu = new Menu();

        menu.setBranch(branches);
        menu.setCreatedAt(LocalDateTime.now());
        menu.setUpdatedAt(LocalDateTime.now());

        menuRepo.save(menu);
        log.info("Menu created for the branch with id: {}", menuRequestDto.branchId());

        return "Menu created for the branch with id: " + menuRequestDto.branchId();

    }


    public Menu getById(Long id) {
        return menuRepo.findById(id).orElseThrow(() -> new CustomException
                (HttpStatus.NOT_FOUND, "menu not found with the given id"));
    }

    public MenuResponseDto getMenu(Long menuId) {
        Menu menu = getById(menuId);

        List<DishResponseDto> dishResponseDtoList = new ArrayList<>();
        menu.getDishes().forEach(dish ->
        {
            List<DishImageResponseDto> imageResponseDtoList = dish.getDishImageList().stream().map(image -> new DishImageResponseDto(image.getId(), image.getImage(), image.getReferenceText())).toList();
            dishResponseDtoList.add(new DishResponseDto(dish.getId(), dish.getName(), dish.getDescription(), dish.getCalories(), dish.getIngredient(), dish.getCategory(), dish.getCuisine(), dish.getIsAvailable(), dish.getPrice(), imageResponseDtoList, dish.getMenu().getId(), dish.getCreatedAt(), dish.getUpdatedAt()));
        });

        MenuResponseDto responseDto = new MenuResponseDto(menu.getId(), dishResponseDtoList, menu.getBranch().getId(), menu.getCreatedAt(), menu.getUpdatedAt());

        log.info("Menu response: {}", responseDto);
        return responseDto;


    }
}
