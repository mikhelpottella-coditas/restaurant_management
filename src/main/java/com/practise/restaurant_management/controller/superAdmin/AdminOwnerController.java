package com.practise.restaurant_management.controller.superAdmin;

import com.practise.restaurant_management.dto.request.SendOwnerInviteRequestDto;
import com.practise.restaurant_management.service.InviteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/invitations/restaurant-owner/send")
public class AdminOwnerController {

    private final InviteService inviteService;

    @PostMapping
    public ResponseEntity<String> inviteRestaurantOwner(@RequestBody @Valid SendOwnerInviteRequestDto request) {
        return ResponseEntity.ok(inviteService.inviteUser(request));
    }

}
