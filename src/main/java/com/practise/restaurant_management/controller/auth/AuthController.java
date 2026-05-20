package com.practise.restaurant_management.controller.auth;

import com.practise.restaurant_management.dto.request.LoginDto;
import com.practise.restaurant_management.dto.request.RegisterRequestDto;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;

    @Operation(
            summary = "this is for the owner to register him sle"
    )
    @PostMapping("/register/owner/{token}")
    public String registerOwner(@PathVariable UUID token,@Valid @RequestBody RegisterRequestDto user){
        return userService.registerOwner(token,user);
    }

    @PostMapping("/register/manager/{token}")
    public String registerManger(@PathVariable UUID token,@Valid @RequestBody RegisterRequestDto user){
        return userService.registerManager(token,user);
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user){
         userService.register(user);
         return "registerd";
    }


    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto loginDto) {
        log.info("User login attempt for username");
        String result = userService.login(loginDto);
        log.info("User logged in successfully");
        return result;
    }

    @PostMapping("/refresh-token/{refreshToken}")
    public String refresh(@PathVariable String refreshToken) {
        return userService.refresh(refreshToken);
    }


}
