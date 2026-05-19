package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.response.OwnerProfileDto;
import com.practise.restaurant_management.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final UserService userService;


    public OwnerProfileDto getOwnerProfile(Long id) {
        User  user = userService.findById(id);
        return new OwnerProfileDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getRole(), user.getImage(), user.getCreatedAt(), user.getUpdatedAt());
    }


    public String updateProfile(Long id, OwnerProfileDto ownerProfileDto) {

        User  owner = userService.findById(id);

        if (ownerProfileDto.firstName() != null) owner.setFirstName(ownerProfileDto.firstName());
        if (ownerProfileDto.lastName() != null) owner.setLastName(ownerProfileDto.lastName());
        if (ownerProfileDto.email() != null) owner.setEmail(ownerProfileDto.email());
        if(ownerProfileDto.phoneNumber() != null) owner.setPhoneNumber(ownerProfileDto.phoneNumber());
        if(ownerProfileDto.image() != null) owner.setImage(ownerProfileDto.image());
        if(ownerProfileDto.updatedAt() != null) owner.setUpdatedAt(LocalDateTime.now());

        return userService.updateProfile(owner);

    }
}
