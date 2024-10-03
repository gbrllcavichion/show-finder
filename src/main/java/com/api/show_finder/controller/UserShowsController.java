package com.api.show_finder.controller;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.services.EventimService;
import com.api.show_finder.services.SpotifyService;
import com.api.show_finder.services.TicketScrapingService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shows")
public class UserShowsController {
    private final SpotifyService spotifyService;
    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public UserShowsController(SpotifyService spotifyService,
                               TicketScrapingService ticketScrapingService,
                               EventimService eventimService,
                               OAuth2AuthorizedClientService authorizedClientService) {
        this.spotifyService = spotifyService;
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/match-favorite-shows")
    public List<ConcertDetails> getMatchedFavoriteShows(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName()
        );

        if (client == null || client.getAccessToken() == null) {
            throw new RuntimeException("Token de acesso não encontrado");
        }

        String accessToken = client.getAccessToken().getTokenValue();
        List<String> favoriteArtists = spotifyService.getUserTopArtists(accessToken);

        List<ConcertDetails> allShows = new ArrayList<>();
        allShows.addAll(ticketScrapingService.fetchConcertDetails());
        allShows.addAll(eventimService.fetchInternationalShows());

        List<ConcertDetails> matchedShows = allShows.stream()
                .filter(show -> favoriteArtists.stream().anyMatch(artist -> show.getEvent().toLowerCase().contains(artist.toLowerCase())))
                .collect(Collectors.toList());

        return matchedShows;
    }
}
