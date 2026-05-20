package com.practise.restaurant_management.dto.response;

public record ViewReportResponseDto(
        Long branchId,
         Double totalIncome,
         Double totalExpenditure,
         Long totalOrders,
         Double totalProfit,
         Double profitPercentage
) {
}

