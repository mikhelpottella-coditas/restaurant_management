package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.DishImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishImageRepo extends JpaRepository<DishImage, Long> {
}
