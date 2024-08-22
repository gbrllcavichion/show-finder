package com.api.show_finder.api.service;

import com.api.show_finder.api.dto.LoginRequest;
import com.api.show_finder.client.AuthSpotifyClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthSpotifyClient authSpotifyClient;

    public AuthService(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    public String getAccessToken(String clientId, String clientSecret) {
        LoginRequest request = new LoginRequest("client_credentials", clientId, clientSecret);
        return authSpotifyClient.login(request).getAccessToken();
    }
}

