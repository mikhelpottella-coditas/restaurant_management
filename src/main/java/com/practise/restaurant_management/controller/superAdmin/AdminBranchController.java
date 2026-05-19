package com.practise.restaurant_management.controller.superAdmin;

import com.practise.restaurant_management.dto.response.BranchResponseDto;
import com.practise.restaurant_management.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/branches")
public class AdminBranchController {

    private final BranchService branchService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<BranchResponseDto>> getAllBranches(@PathVariable Long restaurant){
        log.info("getAllBranches");
        List<BranchResponseDto> branchResponseDtoList = branchService.getAllBranches(restaurant);
        return ResponseEntity.ok(branchResponseDtoList);
    }

    @GetMapping("/byId/{branchId}")
    public  ResponseEntity<BranchResponseDto> getAllBranchesById(@PathVariable Long branchId){
        log.info("get Branches By Id");
        BranchResponseDto branchResponseDto = branchService.getBranchById(branchId);
        return ResponseEntity.ok(branchResponseDto);
    }


}
