package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepo extends JpaRepository<Dishes, Long> {

    List<Dishes> findAllByMenuId(Long branchId);
}
