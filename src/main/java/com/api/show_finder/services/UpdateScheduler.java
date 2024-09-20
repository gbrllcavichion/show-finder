package com.api.show_finder.services;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpdateScheduler {

    private final SpotifyService spotifyService;
    private final EventimService eventimService;
    private final UserRepository userRepository;
    private final MatchService matchService;

    public UpdateScheduler(SpotifyService spotifyService, EventimService eventimService, UserRepository userRepository, MatchService matchService) {
        this.spotifyService = spotifyService;
        this.eventimService = eventimService;
        this.userRepository = userRepository;
        this.matchService = matchService;
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void updateFavoriteArtists() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<Artist> topArtists = spotifyService.getUserTopArtists(user.getSpotifyToken());
            List<String> artistNames = topArtists.stream().map(Artist::getName).collect(Collectors.toList());
            user.setFavoriteArtists(artistNames);
            userRepository.save(user);
        }
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void updateConcertsAndMatch() {
        List<ConcertDetails> concerts = eventimService.fetchInternationalShows();
        matchService.matchFavoriteArtistsWithConcerts(concerts);
    }
}
