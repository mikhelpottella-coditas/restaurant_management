package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branches, Long> {
    List<Branches> findAllByRestaurantId(Long restaurantId);
}
