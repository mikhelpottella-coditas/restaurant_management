package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepositoryImplementation<Order,Long> {

    List<Order> findAllByStaffId(Long staffId);
}
