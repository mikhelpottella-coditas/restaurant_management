package com.practise.restaurant_management.controller.owner;

import com.practise.restaurant_management.dto.response.OwnerProfileDto;
import com.practise.restaurant_management.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/profile")
@RequiredArgsConstructor
@Slf4j
public class OwnerProfileController {

    private final OwnerService ownerService;

    @GetMapping("/{id}")
    public OwnerProfileDto getOwnerProfile(@PathVariable Long id) {
        log.info("get owner profile : {}", id);
        return ownerService.getOwnerProfile(id);
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @RequestBody OwnerProfileDto ownerProfileDto) {
        log.info("update owner profile : {}", ownerProfileDto.toString());
        return ownerService.updateProfile(id, ownerProfileDto);
    }

}
