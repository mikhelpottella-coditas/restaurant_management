package com.practise.restaurant_management.service;

import com.practise.restaurant_management.dto.request.SendOwnerInviteRequestDto;
import com.practise.restaurant_management.entity.User;
import com.practise.restaurant_management.repo.InviteRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InviteService {

    private final InviteRepo inviteRepo;
    private final JavaMailSender javaMailSender;

    public String inviteUser(@Valid SendOwnerInviteRequestDto request) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("mikhel.pottella@coditas.com");
        mailMessage.setTo(request.sentTo());
        mailMessage.setSubject("Invitation to on the application as a owner");
        mailMessage.setText(request.message()+"\n invitation link : https://santa-disobey-washtub.ngrok-free.dev ");
        javaMailSender.send(mailMessage);
        return "invitation sent successfully";


    }
}
