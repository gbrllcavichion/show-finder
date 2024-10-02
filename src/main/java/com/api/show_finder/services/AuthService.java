package com.api.show_finder.services;

import com.api.show_finder.api.response.LoginResponse;
import com.api.show_finder.client.AuthSpotifyClient;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthSpotifyClient authSpotifyClient;
    private final UserRepository userRepository;

    public AuthService(AuthSpotifyClient authSpotifyClient, UserRepository userRepository) {
        this.authSpotifyClient = authSpotifyClient;
        this.userRepository = userRepository;
    }

    public void saveSpotifyToken(String userId, String accessToken) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setSpotifyToken(accessToken);
            userRepository.save(user);
        }
    }
}
