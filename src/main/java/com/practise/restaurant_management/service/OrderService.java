package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.OrderItemRequestDto;
import com.practise.restaurant_management.dto.request.OrderRequestDto;
import com.practise.restaurant_management.dto.response.DishResponseDto;
import com.practise.restaurant_management.dto.response.OrderItemResponseDto;
import com.practise.restaurant_management.dto.response.OrderResponseDto;
import com.practise.restaurant_management.entity.Branches;
import com.practise.restaurant_management.entity.Order;
import com.practise.restaurant_management.entity.OrderItem;
import com.practise.restaurant_management.entity.Staff;
import com.practise.restaurant_management.enums.BranchType;
import com.practise.restaurant_management.enums.OrderStatus;
import com.practise.restaurant_management.enums.PaymentStatus;
import com.practise.restaurant_management.exception.CustomException;
import com.practise.restaurant_management.repo.OrderItemRepo;
import com.practise.restaurant_management.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final StaffService staffService;
    private final BranchService branchService;
    private final DishService dishService;

    public List<OrderResponseDto> getAllOrder(Long staffId) {
        log.info("getAllOrders for the id {}", staffId);
        List<Order> orderList = orderRepo.findAllByStaffId(staffId);

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        orderList.forEach(order -> {
            List<OrderItemResponseDto> itemResponseDtoList = order.getOrderItems().stream().map(item-> new OrderItemResponseDto(item.getId(), item.getQuantity(), item.getPreferences(), item.getTotalPrice(), item.getDishes().getId(), item.getOrder().getId())).toList();
            orderResponseDtoList.add(new OrderResponseDto(order.getId(), order.getOrderedAt(), order.getStaff().getId(), order.getRestaurantTable().getId(), order.getCustomerName(), order.getCustomerNumber(), order.getTaxableAmount(), order.getDiscountAmount(), order.getFinalPrice(),order.getPaymentStatus(),itemResponseDtoList));
        });

        log.info("get all orders of a staff by id {}", staffId);
        return orderResponseDtoList;
    }

    public String createOrder(Long staffId, OrderRequestDto orderRequestDto) {
        Staff staff = staffService.getById(staffId);

        List<OrderItem> orderItemList = new ArrayList<>();

        orderRequestDto.orderItemRequestDtoList().stream().forEach(item-> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderStatus(OrderStatus.PENDING);
            orderItem.setQuantity(item.quantity());
            orderItem.setPreferences(item.preferences());
            orderItem.setDishes(dishService.getById(item.DishId()));
            orderItem.setTotalPrice(orderItem.getQuantity()*orderItem.getDishes().getPrice());
            orderItemList.add(orderItem);
        });


        log.info("createOrder for the id {}", staffId);
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        order.setStaff(staff);
        order.setCustomerName(orderRequestDto.customerName());
        order.setCustomerNumber(orderRequestDto.customerNumber());
        orderItemList.forEach(order::addOrderItem);
        order.setDiscountAmount(orderRequestDto.discountAmount());


        Branches branch = branchService.getById(staff.getBranches().getId());
        Double price =0.0;
        for (OrderItem orderItem : orderItemList) {
            price+=orderItem.getTotalPrice();
        }
        if(branch.getBranchType() == BranchType.LUXURY) order.setTaxableAmount(price*0.18);
        else order.setTaxableAmount(price*.05);

        order.setFinalPrice(price+order.getTaxableAmount()-order.getDiscountAmount());
        order.setPaymentStatus(PaymentStatus.PENDING);

        orderRepo.save(order);
        log.info("create a new order with id {}", order.getId());

        return "created a new order with id : "+order.getId();
    }

    public Order getById(Long orderId){
        return orderRepo.findById(orderId).orElseThrow(()-> new CustomException(HttpStatus.NOT_FOUND, "Order not found with the given id"));
    }

    public String addOrderItem(Long orderId, OrderItemRequestDto orderItemRequestDto) {
        Order order = getById(orderId);
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderStatus(OrderStatus.PENDING);
        orderItem.setTotalPrice(orderItem.getQuantity()*orderItem.getDishes().getPrice());
        orderItem.setDishes(dishService.getById(orderItem.getDishes().getId()));
        orderItem.setPreferences(orderItem.getPreferences());
        orderItem.setQuantity(orderItem.getQuantity());

        order.addOrderItem(orderItem);

        Branches branch = branchService.getById(order.getStaff().getBranches().getId());
        Double price =0.0;
        for (OrderItem item : order.getOrderItems()) {
            price+=item.getTotalPrice();
        }
        if(branch.getBranchType() == BranchType.LUXURY) order.setTaxableAmount(price*0.18);
        else order.setTaxableAmount(price*.05);

        order.setFinalPrice(price+order.getTaxableAmount()-order.getDiscountAmount());
        order.setPaymentStatus(PaymentStatus.PENDING);

        orderRepo.save(order);

        log.info("add new item with id {}", orderItem.getId());
        return "added a new item with id : "+orderItem.getId();

    }


    public List<OrderItemResponseDto> getAllOrderItems(Long branchId) {

        Branches branch = branchService.getById(branchId);

        List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
        branch.getStaffList().forEach(staff -> staff.getOrder().stream().filter(o -> o.getPaymentStatus() == PaymentStatus.PENDING).toList().forEach(order -> order.getOrderItems().stream().filter(i -> i.getOrderStatus() == OrderStatus.PENDING || i.getOrderStatus() == OrderStatus.ACCEPTED ).toList().forEach(orderItem -> {
            orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem.getId(), orderItem.getQuantity(), orderItem.getPreferences(), orderItem.getTotalPrice(), orderItem.getDishes().getId(), orderItem.getOrder().getId()));
        })));

        log.info("getAllOrderItems for branch which or pending to serve{}", branchId);
        return orderItemResponseDtoList;

    }
}
