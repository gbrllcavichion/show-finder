package com.api.show_finder.api.controller;

import com.api.show_finder.api.dto.Artist;
import com.api.show_finder.api.dto.ArtistRepository;
import com.api.show_finder.api.service.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final SpotifyService spotifyService;
    private final ArtistRepository artistRepository;

    public ArtistController(SpotifyService spotifyService, ArtistRepository artistRepository) {
        this.spotifyService = spotifyService;
        this.artistRepository = artistRepository;
    }

    @GetMapping("/top")
    public List<Artist> getTopArtists(@RequestHeader("Authorization") String token) {
        return spotifyService.getUserTopArtists(token);
    }
}
