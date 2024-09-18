package com.api.show_finder.services;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public MatchService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void matchFavoriteArtistsWithConcerts(List<ConcertDetails> concerts) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (String artist : user.getFavoriteArtists()) {
                for (ConcertDetails concert : concerts) {
                    if (concert.getEvent().toLowerCase().contains(artist.toLowerCase())) {
                        notificationService.sendConcertNotification(user, concert);
                    }
                }
            }
        }
    }
}
