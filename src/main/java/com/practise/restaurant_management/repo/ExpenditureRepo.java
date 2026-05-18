package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenditureRepo extends JpaRepository<Expenditure,Long> {
}
