package com.practise.restaurant_management.controller.superAdmin;

import com.practise.restaurant_management.dto.request.ViewReportRequestDto;
import com.practise.restaurant_management.dto.response.BranchResponseDto;
import com.practise.restaurant_management.dto.response.ViewReportResponseDto;
import com.practise.restaurant_management.service.BranchService;
import com.practise.restaurant_management.service.RevenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/branches")
public class AdminBranchController {

    private final BranchService branchService;
    private final RevenueService revenueService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<BranchResponseDto>> getAllBranches(@PathVariable Long restaurant,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size,
                                                                  @RequestParam(defaultValue = "id") String sortBy,
                                                                  @RequestParam(defaultValue = "true") boolean ascending) {
        log.info("getAllBranches");
        List<BranchResponseDto> branchResponseDtoList = branchService.getAllBranches(restaurant, page, size, sortBy, ascending);
        return ResponseEntity.ok(branchResponseDtoList);
    }

    @GetMapping("/byId/{branchId}")
    public  ResponseEntity<BranchResponseDto> getAllBranchesById(@PathVariable Long branchId){
        log.info("get Branches By Id");
        BranchResponseDto branchResponseDto = branchService.getBranchById(branchId);
        return ResponseEntity.ok(branchResponseDto);
    }

    @GetMapping("/getReport")
    public ResponseEntity<List<ViewReportResponseDto>> getExpenditureAmount(@Valid @RequestBody ViewReportRequestDto viewReportRequestDto) {
        List<ViewReportResponseDto> viewReportResponseDtoList = revenueService.viewReports(viewReportRequestDto);
        return ResponseEntity.ok(viewReportResponseDtoList);
    }



}
