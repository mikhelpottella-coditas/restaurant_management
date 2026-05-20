package com.practise.restaurant_management.service;

import com.practise.restaurant_management.repo.ExpenditureRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExpenditureServiceTest {

    @Mock
    private ExpenditureRepo expenditureRepo;

    @Mock
    private BranchService branchService;

    @InjectMocks
    private  ExpenditureService expenditureService;



    @Test
    void createExpenditure() {



    }

    @Test
    void getAll() {
    }

    @Test
    void getAllExpenditure() {
    }
}