package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.SendOwnerInviteRequestDto;
import com.practise.restaurant_management.entity.Invite;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.enums.InviteStatus;
import com.practise.restaurant_management.repo.InviteRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InviteService {

    private final InviteRepo inviteRepo;
    private final JavaMailSender javaMailSender;

    public String inviteUser(SendOwnerInviteRequestDto request,String path) {

        Invite invite = new Invite();

        invite.setInviteStatus(InviteStatus.PENDING);
        invite.setInviteToken(UUID.randomUUID());
        invite.setInvitationTo(request.sentTo());
        invite.setCreationDate(LocalDateTime.now());
        invite.setExpirationDate(LocalDateTime.now().plusDays(2));

        inviteRepo.save(invite);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("mikhel.pottella@coditas.com");
        mailMessage.setTo(request.sentTo());
        mailMessage.setSubject("Invitation to on the application as a owner");
        mailMessage.setText(request.message()+"\n**this link will expire in next 48hrs \n invitation link : https://santa-disobey-washtub.ngrok-free.dev"+path+invite.getInviteToken());

        javaMailSender.send(mailMessage);
        return "invitation sent successfully";


    }

    public String inviteOwner(@Valid SendOwnerInviteRequestDto request) {
        inviteUser(request, "/auth/register/owner/");
        return "invitation sent successful";
    }

    public Boolean validate(String email, UUID token) {
        Invite invite = inviteRepo.findByInviteToken(token);
        return email.equals(invite.getInvitationTo());
    }

    public String inviteManager(@Valid SendOwnerInviteRequestDto request) {
        inviteUser(request, "/auth/register/manager/");
        return "invitation sent successful";
    }
}
