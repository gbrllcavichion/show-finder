package com.api.show_finder.api.service;

import com.api.show_finder.api.dto.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotifyService {
    private final AuthService authService;

    public SpotifyService(AuthService authService) {
        this.authService = authService;
    }

    public List<Artist> getUserTopArtists(String accessToken) {
        // chamada REST para o endpoint Spotify usando RestTemplate/Webclient
        // parsear o JSON e converter para uma lista de objetos Artist
        return null;
    }
}
