package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.response.ManagerProfileDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.enums.Role;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.BranchRepo;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private static final Logger log = LogManager.getLogger(ManagerService.class);
    private final UserService userService;
    private final BranchRepo branchRepo;

    public User findById(Long id){
        return userService.findById(id);
    }


    public ManagerProfileDto getProfile(Long id) {
        User user = userService.findById(id);
        return new ManagerProfileDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getImage(), user.getCreatedAt(), user.getUpdatedAt());
    }

    public String updateProfile(Long id, ManagerProfileDto managerProfileDto) {
        User user = userService.findById(id);
        if(managerProfileDto.firstName()!=null) user.setFirstName(managerProfileDto.firstName());
        if(managerProfileDto.lastName()!=null) user.setLastName(managerProfileDto.lastName());
        if(managerProfileDto.email()!=null) user.setEmail(managerProfileDto.email());
        if(managerProfileDto.image()!=null) user.setImage(managerProfileDto.image());
        if (managerProfileDto.phoneNumber()!=null) user.setPhoneNumber(managerProfileDto.phoneNumber());
        user.setUpdatedAt(LocalDateTime.now());

        return userService.updateProfile(user);
    }




    public List<ManagerProfileDto> getAllManagers() {
        List<User> managers = userService.getAllUsersRoleManager(Role.MANAGER);
        log.info("Retrieved {} managers from the database", managers.size());
        return managers.stream().map(manager -> new ManagerProfileDto(manager.getId(), manager.getFirstName(), manager.getLastName(), manager.getEmail(),
                manager.getPhoneNumber(), manager.getRole(), manager.getImage(),
                manager.getCreatedAt(),manager.getUpdatedAt())).toList();
    }

    public String assignBranchToManager(Long managerId, Long branchId) {
        Branches branches = branchRepo.findById(branchId).orElseThrow(()->new CustomException(HttpStatus.NOT_FOUND, "Branch not found with the given ID"));
        User manager = userService.findById(managerId);
        branches.setManager(manager);
        manager.setBranches(branches);
        branchRepo.save(branches);
        userService.save(manager);
        log.info("Branch with ID {} has been assigned to Manager with ID {}", branchId, managerId);
        return "branch assigned to manager successfully";
    }
}
