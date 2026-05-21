package com.practise.restaurant_management.service;

import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.repo.BranchRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepo branchRepo;

    @Test
    void getById() {

      Branches branches = new Branches();
      branches.setId(1L);

        Mockito.when(branchRepo.findById(1L)).thenReturn(Optional.of(branches));

        assertEquals(branches,branchService.getById(1L));
    }


    @Test
    void getAll() {

        List<Branches> branches = new ArrayList<>();

        Mockito.when(branchRepo.findAll()).thenReturn(branches);

        assertEquals(branches, branchService.getAll());
    }



    @Test
    void updateBranch() {

        Branches branches = new Branches();

        branches.setId(1L);

        Mockito.when(branchRepo.findById(1L)).thenReturn(Optional.of(branches));


        assertEquals(branches,branchService.getById(1L));
    }


}