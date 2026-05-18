package com.practise.restaurant_management.controller.auth;

import com.practise.restaurant_management.dto.request.LoginDto;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        userService.register(user);
        return "success";
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto loginDto) {
        log.info("User login attempt for username");
        String result = userService.login(loginDto);
        log.info("User logged in successfully");
        return result;
    }

}
