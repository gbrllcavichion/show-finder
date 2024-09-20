package com.api.show_finder.services;

import com.api.show_finder.api.response.LoginResponse;
import com.api.show_finder.client.AuthSpotifyClient;
import com.api.show_finder.domain.model.User;
import com.api.show_finder.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class AuthService {

    private final AuthSpotifyClient authSpotifyClient;
    private final UserRepository userRepository;

    public AuthService(AuthSpotifyClient authSpotifyClient, UserRepository userRepository) {
        this.authSpotifyClient = authSpotifyClient;
        this.userRepository = userRepository;
    }

    public String getAccessTokenFromCode(String code, String clientId, String clientSecret, String redirectUri) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", redirectUri);
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);

        LoginResponse response = authSpotifyClient.loginWithAuthorizationCode(body);
        return response.getAccessToken();
    }
}
