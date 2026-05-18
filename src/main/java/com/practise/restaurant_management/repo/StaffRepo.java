package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Staff;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends JpaRepositoryImplementation<Staff,Long> {
}
