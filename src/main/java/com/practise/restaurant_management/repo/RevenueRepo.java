package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepo extends JpaRepository<Revenue,Long> {
}
