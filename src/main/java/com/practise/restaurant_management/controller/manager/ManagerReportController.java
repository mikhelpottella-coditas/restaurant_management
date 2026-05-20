package com.practise.restaurant_management.controller.manager;


import com.practise.restaurant_management.dto.request.ViewReportRequestDto;
import com.practise.restaurant_management.dto.response.ViewReportResponseDto;
import com.practise.restaurant_management.service.RevenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/manager/branches/analytics")
public class ManagerReportController {

    RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<ViewReportResponseDto>> getExpenditureAmount(@Valid @RequestBody ViewReportRequestDto viewReportRequestDto) {
        List<ViewReportResponseDto> viewReportResponseDtoList = revenueService.viewReports(viewReportRequestDto);
        return ResponseEntity.ok(viewReportResponseDtoList);
    }

}
