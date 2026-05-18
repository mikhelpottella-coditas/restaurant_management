package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.OrderItem;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepositoryImplementation<OrderItem,Long> {
}
