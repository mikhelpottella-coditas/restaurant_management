package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dishrepo extends JpaRepository<Dishes, Long> {
}
