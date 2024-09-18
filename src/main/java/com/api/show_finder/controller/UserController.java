package com.api.show_finder.controller;

import com.api.show_finder.api.dto.UserRegistrationRequest;
import com.api.show_finder.domain.model.Artist;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.model.UserProfile;
import com.api.show_finder.services.AuthService;
import com.api.show_finder.services.SpotifyService;
import com.api.show_finder.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AuthService authService;
    private final SpotifyService spotifyService;
    private final UserService userService;

    public UserController(AuthService authService, SpotifyService spotifyService, UserService userService) {
        this.authService = authService;
        this.spotifyService = spotifyService;
        this.userService = userService;
    }

    @PostMapping("/{userId}/connect-spotify")
    public ResponseEntity<User> connectSpotify(@PathVariable String userId,
                                               @RequestParam String code,
                                               @RequestParam String clientId,
                                               @RequestParam String clientSecret,
                                               @RequestParam String redirectUri) {
        try {
            String accessToken = authService.getAccessTokenFromCode(code, clientId, clientSecret, redirectUri);

            List<Artist> favoriteArtists = spotifyService.getUserTopArtists(accessToken);

            User user = userService.connectSpotify(userId, favoriteArtists, accessToken);

            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
