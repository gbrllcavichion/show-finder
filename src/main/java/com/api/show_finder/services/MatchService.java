package com.api.show_finder.services;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.ConcertDetails;
import org.bson.types.ObjectId;
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

    public List<ConcertDetails> getMatchingConcertsForUser(String spotifyToken, ObjectId userId) {
        List<Artist> favoriteArtists = spotifyService.getUserTopArtists(spotifyToken);

        List<ConcertDetails> availableConcerts = showsService.getUserFavoriteConcerts(userId);

        List<ConcertDetails> matchingConcerts = availableConcerts.stream()
                .filter(concert -> favoriteArtists.stream()
                        .anyMatch(artist -> concert.getEvent().toLowerCase().contains(artist.getName().toLowerCase()))
                )
                .collect(Collectors.toList());

        return matchingConcerts;
    }

}
