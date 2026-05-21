package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepositoryImplementation<Staff,Long> {
    Page<Staff> findAllByManagerUserId(Long managerId, Pageable pageable);
}
