package com.practise.restaurant_management.controller.manager;

import com.practise.restaurant_management.dto.response.ManagerProfileDto;
import com.practise.restaurant_management.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/profile")
@Slf4j
public class ManagerProfileController {

    private final ManagerService managerService;

    @GetMapping("/{id}")
    public ManagerProfileDto getProfile(@PathVariable Long id) {
        log.info("get owner profile : {}", id);
        return managerService.getProfile(id);
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody ManagerProfileDto managerProfileDto) {
       log.info("update owner profile : {}", managerProfileDto.firstName());
        return managerService.updateProfile(id,managerProfileDto);
    }




}
