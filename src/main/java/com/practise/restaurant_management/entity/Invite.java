package com.practise.restaurant_management.entity;

import com.practise.restaurant_management.enums.InviteStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String invitationTo;

    private UUID inviteToken;

    private InviteStatus inviteStatus;

    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;

}
