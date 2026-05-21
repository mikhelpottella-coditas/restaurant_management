package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.OrderItem;
import com.practise.restaurant_management.entity.Restaurant;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepositoryImplementation<Restaurant,Long> {
}
