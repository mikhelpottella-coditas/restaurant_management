package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Revenue;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RevenueRepo extends JpaRepository<Revenue,Long> {

    @Query("""
        SELECT b FROM Revenue b WHERE b.branch.id = :branchId AND
        b.date BETWEEN :from AND :to
    """)
    List<Revenue> extractDetailsBtw(@NotNull Long aLong, @NotNull LocalDate from, @NotNull LocalDate localDate);
}
