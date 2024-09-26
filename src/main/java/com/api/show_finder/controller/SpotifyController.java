package com.api.show_finder.controller;

import com.api.show_finder.api.response.LoginResponse;
import com.api.show_finder.client.AuthSpotifyClient;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    @Autowired
    private AuthSpotifyClient authSpotifyClient;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/callback")
    public ResponseEntity<?> spotifyCallback(@RequestParam("code") String code, @RequestParam("state") String userId) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", "YOUR_REDIRECT_URI");
        body.add("client_id", "YOUR_SPOTIFY_CLIENT_ID");
        body.add("client_secret", "YOUR_SPOTIFY_CLIENT_SECRET");

        LoginResponse spotifyResponse = authSpotifyClient.loginWithAuthorizationCode(body);

        Optional<User> user = userRepository.findById(Long.valueOf(userId));
        if (user.isPresent()) {
            user.get().setSpotifyToken(spotifyResponse.getAccessToken());
            userRepository.save(user.get());
            return ResponseEntity.ok("Spotify autenticado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
