package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.LoginDto;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.enums.Role;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.UserRepo;
import com.practise.restaurant_management.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private  final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username);
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"User not found"));
    }

    public String login(@Valid LoginDto loginDto) {
        log.info("Logging in user");
        User user = userRepo.findByEmail(loginDto.email());
        if(user == null) {
            log.warn("Invalid credentials for user");
            throw  new CustomException(HttpStatus.NOT_FOUND, "Invalid credentials");
        }
        if(!passwordEncoder.matches(loginDto.password(), user.getPassword())){
            log.warn("Invalid password for user");
            throw  new CustomException(HttpStatus.NOT_FOUND, "Invalid credentials");
        }

        log.info("User logged in successfully");
        return jwtUtil.generateToken(user.getUsername(), user.getEmail());
    }

    public String updateProfile( User user) {
        userRepo.save(user);
        return "user updated successfully";
    }

    public List<User> getAllUsersRoleManager(Role role) {
        return userRepo.findByRole(role);
    }

    public void save(User manager) {
        userRepo.save(manager);
    }

    public void delete(User staffUser) {
        userRepo.delete(staffUser);
    }


}
