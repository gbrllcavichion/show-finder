package com.api.show_finder.services;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowsService {

    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;
    private final UserRepository userRepository;

    public ShowsService(TicketScrapingService ticketScrapingService, EventimService eventimService, UserRepository userRepository) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
        this.userRepository = userRepository;
    }

    public List<ConcertDetails> getUserFavoriteConcerts(ObjectId userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado.");
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

        return favoriteArtistShows;
    }

    public List<ConcertDetails> getAllAvailableConcerts() {
        List<ConcertDetails> allShows = new ArrayList<>();
        List<ConcertDetails> ticketMasterShows = ticketScrapingService.fetchConcertDetails();
        List<ConcertDetails> eventimShows = eventimService.fetchInternationalShows();

        allShows.addAll(ticketMasterShows);
        allShows.addAll(eventimShows);

        return allShows;
    }
}
