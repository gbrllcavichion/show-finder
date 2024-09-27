package com.api.show_finder.services;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.ConcertDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final SpotifyService spotifyService;
    private final ShowsService showsService;

    public MatchService(SpotifyService spotifyService, ShowsService showsService) {
        this.spotifyService = spotifyService;
        this.showsService = showsService;
    }
    public List<ConcertDetails> getMatchingConcertsForUser(String spotifyToken) {
        List<Artist> favoriteArtists = spotifyService.getUserTopArtists(spotifyToken);
        List<ConcertDetails> availableConcerts = showsService.getAllAvailableConcerts();

        List<ConcertDetails> matchingConcerts = availableConcerts.stream()
                .filter(concert -> favoriteArtists.stream()
                        .anyMatch(artist -> concert.getEvent().contains(artist.getName()))
                )
                .collect(Collectors.toList());

        System.out.println("Shows correspondentes: " + matchingConcerts);

        return matchingConcerts;
    }

}
