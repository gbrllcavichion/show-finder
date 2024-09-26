package com.api.show_finder.controller;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import com.api.show_finder.services.EventimService;
import com.api.show_finder.services.TicketScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shows")
public class ShowsController {
    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;
    private final UserRepository userRepository;

    public ShowsController(TicketScrapingService ticketScrapingService, EventimService eventimService, UserRepository userRepository) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
        this.userRepository = userRepository;
    }

    @GetMapping("/fetch-ticketmaster-concerts")
    public List<ConcertDetails> fetchConcerts() {
        return ticketScrapingService.fetchConcertDetails();
    }

    @GetMapping("/fetch-international-shows")
    public List<ConcertDetails> fetchInternationalShows() {
        return eventimService.fetchInternationalShows();
    }

    @GetMapping("/user-shows/{userId}")
    public ResponseEntity<List<ConcertDetails>> getUserFavoriteArtistShows(@PathVariable String userId) {
        Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        User user = userOptional.get();
        List<String> favoriteArtists = user.getFavoriteArtists();

        List<ConcertDetails> allShows = new ArrayList<>();
        List<ConcertDetails> ticketMasterShows = ticketScrapingService.fetchConcertDetails();
        List<ConcertDetails> eventimShows = eventimService.fetchInternationalShows();

        allShows.addAll(ticketMasterShows);
        allShows.addAll(eventimShows);

        List<ConcertDetails> favoriteArtistShows = allShows.stream()
                .filter(show -> favoriteArtists.stream()
                        .anyMatch(artist -> show.getEvent().toLowerCase().contains(artist.toLowerCase())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(favoriteArtistShows);
    }
}
