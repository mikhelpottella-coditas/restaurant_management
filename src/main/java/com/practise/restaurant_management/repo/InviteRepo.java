package com.practise.restaurant_management.repo;

import com.practise.restaurant_management.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InviteRepo extends JpaRepository<Invite,Long> {
    Invite findByInviteToken(UUID token);
}

