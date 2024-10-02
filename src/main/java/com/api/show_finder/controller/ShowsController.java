package com.api.show_finder.controller;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import com.api.show_finder.services.EventimService;
import com.api.show_finder.services.SpotifyService;
import com.api.show_finder.services.TicketScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "http://localhost:3000")
public class ShowsController {

    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;
    private final UserRepository userRepository;
    private final SpotifyService spotifyService;

    public ShowsController(TicketScrapingService ticketScrapingService, EventimService eventimService,
                           UserRepository userRepository, SpotifyService spotifyService) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
        this.userRepository = userRepository;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/user-shows/{userId}")
    public ResponseEntity<List<ConcertDetails>> getUserFavoriteArtistShows(@PathVariable String userId) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User user = userOptional.get();
        String spotifyToken = user.getSpotifyToken();

        List<Artist> favoriteArtists = spotifyService.getUserTopArtists(spotifyToken);

        List<ConcertDetails> allShows = new ArrayList<>();
        List<ConcertDetails> ticketMasterShows = ticketScrapingService.fetchConcertDetails();
        List<ConcertDetails> eventimShows = eventimService.fetchInternationalShows();

        allShows.addAll(ticketMasterShows);
        allShows.addAll(eventimShows);

        List<ConcertDetails> favoriteArtistShows = allShows.stream()
                .filter(show -> favoriteArtists.stream()
                        .anyMatch(artist -> show.getEvent().toLowerCase().contains(artist.getName().toLowerCase())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(favoriteArtistShows);
    }
}