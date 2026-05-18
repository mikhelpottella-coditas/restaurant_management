package com.practise.restaurant_management.controller.manager.profile;

import com.practise.restaurant_management.dto.response.ManagerProfileDto;
import com.practise.restaurant_management.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/profile")
public class ManagerProfileController {

    private final ManagerService managerService;

    @GetMapping("/{id}")
    public ManagerProfileDto getProfile(@PathVariable Long id) {
        return managerService.getProfile(id);
    }

}
