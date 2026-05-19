package com.practise.restaurant_management.controller.owner;

import com.practise.restaurant_management.dto.request.BranchRequestDto;
import com.practise.restaurant_management.dto.response.BranchResponseDto;
import com.practise.restaurant_management.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner/branches")
@Slf4j
public class OwnerBranchController {

    private final BranchService branchService;

    @PostMapping
    public ResponseEntity<String> addBranch(@Valid @RequestBody BranchRequestDto branchRequestDto) {
        log.info("Adding branch with location: {}", branchRequestDto.location());
        String response = branchService.addBranch(branchRequestDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<BranchResponseDto>> getAllBranches(@PathVariable Long restaurantId) {
        log.info("Fetching all branches for restaurant ID: {}", restaurantId);
        List<BranchResponseDto> branches = branchService.getAllBranches(restaurantId);
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchResponseDto> getBranchById(@PathVariable Long branchId) {
        log.info("Fetching branch with ID: {}", branchId);
        BranchResponseDto branch = branchService.getBranchById(branchId);
        return ResponseEntity.ok(branch);
    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long branchId) {
        log.info("Deleting branch with ID: {}", branchId);
        String response = branchService.deleteBranch(branchId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{branchId}")
    public ResponseEntity<String> updateBranch(@PathVariable Long branchId, @RequestBody BranchRequestDto branchRequestDto) {
        log.info("Updating branch with ID: {}", branchId);
        String response = branchService.updateBranch(branchId, branchRequestDto);
        return ResponseEntity.ok(response);
    }

}
