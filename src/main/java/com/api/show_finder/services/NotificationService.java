package com.api.show_finder.services;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConcertNotification(User user, ConcertDetails concert) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Novo show do seu artista favorito!");
        message.setText("Seu artista favorito " + concert.getEvent() + " vai tocar em: " + concert.getEventDate());
        mailSender.send(message);
    }
}
