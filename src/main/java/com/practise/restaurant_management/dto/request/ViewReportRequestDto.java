package com.practise.restaurant_management.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;



public record ViewReportRequestDto (
        @NotNull
        Long branchId,
        @NotNull
        LocalDate from,
        @NotNull
        LocalDate to
){
}

