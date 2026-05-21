package com.practise.restaurant_management.controller.staff;

import com.practise.restaurant_management.dto.request.ExpenditureRequestDto;
import com.practise.restaurant_management.dto.response.ExpenditureResponseDto;
import com.practise.restaurant_management.entity.Expenditure;
import com.practise.restaurant_management.service.ExpenditureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/store/expenditures")
public class InventoryStaffController {

    private final ExpenditureService expenditureService;

    @PostMapping
    public ResponseEntity<String> createExpenditure(@RequestBody ExpenditureRequestDto expenditureRequestDto){
        log.info("create Expenditure for the branch id : {}", expenditureRequestDto.branchesId());
        String response = expenditureService.createExpenditure(expenditureRequestDto);
        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<ExpenditureResponseDto>> getExpenditures(@RequestParam Long branchId){
        log.info("get Expenditures for the branch id : {}", branchId);
        List<ExpenditureResponseDto> expenditureResponseDtoList = expenditureService.getAllExpenditure(branchId);
        return ResponseEntity.ok(expenditureResponseDtoList);
    }

}
