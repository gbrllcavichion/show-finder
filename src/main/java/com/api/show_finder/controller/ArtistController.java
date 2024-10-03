package com.api.show_finder.controller;

import com.api.show_finder.services.SpotifyService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final SpotifyService spotifyService;
    private final OAuth2AuthorizedClientService authorizedClientService;

    public ArtistController(SpotifyService spotifyService, OAuth2AuthorizedClientService authorizedClientService) {
        this.spotifyService = spotifyService;
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/top")
    public List<String> getTopArtists(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName()
        );

        if (client == null || client.getAccessToken() == null) {
            throw new RuntimeException("Token de acesso não encontrado");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        return spotifyService.getUserTopArtists(accessToken);
    }
}