package com.api.show_finder.api.controller;

import com.api.show_finder.api.dto.Artist;
import com.api.show_finder.api.dto.ArtistRepository;
import com.api.show_finder.api.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final SpotifyService spotifyService;

    public ArtistController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/top")
    public List<Artist> getTopArtists(OAuth2AuthenticationToken authentication) {
        OAuth2User user = authentication.getPrincipal();
        String token = authentication.getAuthorizedClientRegistrationId();

        return spotifyService.getUserTopArtists(token);
    }
}