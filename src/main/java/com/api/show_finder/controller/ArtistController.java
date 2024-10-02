package com.api.show_finder.controller;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import com.api.show_finder.services.SpotifyService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final SpotifyService spotifyService;
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final UserRepository userRepository;

    public ArtistController(SpotifyService spotifyService, OAuth2AuthorizedClientService authorizedClientService, UserRepository userRepository) {
        this.spotifyService = spotifyService;
        this.authorizedClientService = authorizedClientService;
        this.userRepository = userRepository;
    }

    @GetMapping("/top")
    public List<Artist> getTopArtists(OAuth2AuthenticationToken authenticationToken) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(),
                authenticationToken.getName()
        );

        if (client == null || client.getAccessToken() == null) {
            throw new RuntimeException("Token de acesso não encontrado");
        }

        String accessToken = client.getAccessToken().getTokenValue();

        List<Artist> topArtists = spotifyService.getUserTopArtists(accessToken);

        String email = authenticationToken.getName();
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFavoriteArtists(topArtists.stream().map(Artist::getName).collect(Collectors.toList()));
            userRepository.save(user);
        }

        return topArtists;
    }
}
