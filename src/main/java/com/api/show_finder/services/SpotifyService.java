package com.api.show_finder.services;

import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.api.response.SpotifyTopArtistsResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SpotifyService {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/me/top/artists";

    public List<Artist> getUserTopArtists(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<SpotifyTopArtistsResponse> response = restTemplate.exchange(
                SPOTIFY_API_URL, HttpMethod.GET, entity, SpotifyTopArtistsResponse.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().getItems();
        } else {
            throw new RuntimeException("Falha ao buscar os artistas do usuário");
        }
    }
}