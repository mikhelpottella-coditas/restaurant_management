package com.practise.restaurant_management.service;

import com.practise.restaurant_management.entity.Order;
import com.practise.restaurant_management.repo.OrderItemRepo;
import com.practise.restaurant_management.repo.OrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private  OrderRepo orderRepo;
    @Mock
    private  OrderItemRepo orderItemRepo;

    @Mock
    private  RestaurantTableService tableService;

    @Mock
    private  StaffService staffService;

    @Mock
    private  BranchService branchService;
    @Mock
    private  DishService dishService;

    @Test
    void getAllOrder() {

        List<Order> orders = new ArrayList<>();

        Mockito.when(orderRepo.findAllByStaffId(1L)).thenReturn(orders);



    }

    @Test
    void createOrder() {
    }

    @Test
    void getById() {
    }

    @Test
    void testGetAllOrder() {
    }

    @Test
    void testCreateOrder() {
    }

    @Test
    void testGetById() {
    }

    @Test
    void addOrderItem() {
    }
}