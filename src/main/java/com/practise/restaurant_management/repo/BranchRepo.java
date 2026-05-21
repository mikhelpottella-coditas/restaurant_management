package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Branches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branches, Long> {
    Page<Branches> findAllByRestaurantId(Long restaurantId, Pageable pageable);
}
