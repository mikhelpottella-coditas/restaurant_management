package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.RestaurantTable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepo extends JpaRepositoryImplementation<RestaurantTable,Long> {
}
