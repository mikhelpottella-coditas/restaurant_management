package com.practise.restaurant_management.controller.manager;

import com.practise.restaurant_management.dto.request.MenuRequestDto;
import com.practise.restaurant_management.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/menu")
@RequiredArgsConstructor
@Slf4j
public class ManagerMenuController {

    private final MenuService menuService;


    @PostMapping
    public ResponseEntity<String> createMenu(@RequestBody MenuRequestDto menuRequestDto) {
        log.info("createMenu for the branch id : {}",menuRequestDto.branchId());
        String response = menuService.createMenu(menuRequestDto);
        return ResponseEntity.ok(response);
    }


}
