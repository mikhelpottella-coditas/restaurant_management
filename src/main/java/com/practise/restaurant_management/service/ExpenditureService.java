package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.ExpenditureRequestDto;
import com.practise.restaurant_management.dto.response.ExpenditureResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Expenditure;
import com.practise.restaurant_management.repo.ExpenditureRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenditureService {

    private final ExpenditureRepo expenditureRepo;
    private final BranchService branchService;

    public String createExpenditure(ExpenditureRequestDto expenditureRequestDto) {
        Branches branches = branchService.getById(expenditureRequestDto.branchesId());

        Expenditure expenditure = new Expenditure();
        expenditure.setBranches(branches);
        expenditure.setAmount(expenditureRequestDto.amount());
        expenditure.setDate(LocalDateTime.now());

        expenditureRepo.save(expenditure);

        log.info("Expenditure created");

        return "new expenditure created on date: " + expenditure.getDate();
    }

    public List<Expenditure> getAll(Long branchId){
        return expenditureRepo.findAllByBranchesId(branchId);
    }

    public List<ExpenditureResponseDto> getAllExpenditure(Long branchId) {
        List<Expenditure> expenditureList =  getAll(branchId);
        List<ExpenditureResponseDto> expenditureResponseDtoList = expenditureList.stream().map(e -> new ExpenditureResponseDto(e.getId(), e.getDate(), e.getAmount(), e.getBranches().getId())).toList();
        log.info("get all expenditures");
        return expenditureResponseDtoList;
    }
}

