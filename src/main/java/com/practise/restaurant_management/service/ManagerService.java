package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.response.ManagerProfileDto;
import com.practise.restaurant_management.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final UserService userService;


    public ManagerProfileDto getProfile(Long id) {
        User user = userService.findById(id);
        return new ManagerProfileDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getImage(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
