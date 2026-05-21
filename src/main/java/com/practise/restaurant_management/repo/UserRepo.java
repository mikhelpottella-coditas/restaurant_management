package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);

    Page<User> findByRole(Role role, Pageable pageable);

}
