package com.practise.restaurant_management.service;


import com.practise.restaurant_management.dto.request.BranchRequestDto;
import com.practise.restaurant_management.dto.response.BranchResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Restaurant;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.BranchRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    private final BranchRepo branchRepo;
    private final RestaurantService restaurantService;
    private final ManagerService managerService;

    public String addBranch(BranchRequestDto branchRequestDto) {

        Restaurant restaurant = restaurantService.getById(branchRequestDto.restaurantId());

        Branches branch = new Branches();
        branch.setLocation(branchRequestDto.location());
        branch.setBranchType(branchRequestDto.branchType());
        branch.setCuisine(branchRequestDto.cuisine());
        branch.setContactNumber(branchRequestDto.contactNumber());
        branch.setRestaurant(restaurant);
        branch.setCreatedAt(LocalDateTime.now());
        branch.setUpdatedAt(LocalDateTime.now());

        branchRepo.save(branch);
        return "new branch added";

    }

    public List<BranchResponseDto> getAllBranches(Long restaurantId) {
        List<Branches> branches = branchRepo.findAllByRestaurantId(restaurantId);

        return branches.stream().map(branch -> new BranchResponseDto(branch.getId(), branch.getRestaurant().getName(),null, branch.getLocation(), branch.getContactNumber(), branch.getCreatedAt(), branch.getUpdatedAt(), branch.getCuisine(), branch.getBranchType(), null, null)).toList();
    }

    public Branches getById(Long branchId) {
        return branchRepo.findById(branchId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "branch not found"));
    }

    public BranchResponseDto getBranchById( Long branchId) {
        Branches branch = branchRepo.findById(branchId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "branch not found"));
        log.info("branch found with the given id: {}", branchId);
        String managerName = branch.getManager() != null ? branch.getManager().getFirstName() : null;
        return new BranchResponseDto(branch.getId(), branch.getRestaurant().getName(),managerName, branch.getLocation(), branch.getContactNumber(), branch.getCreatedAt(), branch.getUpdatedAt(), branch.getCuisine(), branch.getBranchType(), null, null);
    }

    public String deleteBranch(Long branchId) {
        Branches branches =  branchRepo.findById(branchId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "branch not found"));
        log.info("deleting branch with id: {}", branchId);
        branchRepo.deleteById(branchId);
        return "branch deleted successfully";
    }

    public String updateBranch(Long branchId, BranchRequestDto branchRequestDto) {
        Branches branches =  branchRepo.findById(branchId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "branch not found"));

        if(branchRequestDto.location()!=null)branches.setLocation(branchRequestDto.location());
        if(branchRequestDto.branchType()!=null)branches.setBranchType(branchRequestDto.branchType());
        if(branchRequestDto.contactNumber()!=null)branches.setContactNumber(branchRequestDto.contactNumber());
        if(branchRequestDto.cuisine()!=null)branches.setCuisine(branchRequestDto.cuisine());
        if(branchRequestDto.restaurantId()!=null)branches.setRestaurant(restaurantService.getById(branchRequestDto.restaurantId()));
        if(branchRequestDto.managerId()!=null)branches.setManager(managerService.findById(branchRequestDto.managerId()));

        branches.setUpdatedAt(LocalDateTime.now());
        branchRepo.save(branches);
        log.info("updated branch with id: {}", branchId);
        return "branch updated successfully";


    }

    public void save(Branches branches) {
        branchRepo.save(branches);
    }
}
