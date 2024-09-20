package com.api.show_finder.domain.dto;

import java.util.List;

public class UserDto {
    private String email;
    private List<String> favoriteArtists;
    private String spotifyToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFavoriteArtists() {
        return favoriteArtists;
    }

    public void setFavoriteArtists(List<String> favoriteArtists) {
        this.favoriteArtists = favoriteArtists;
    }

    public String getSpotifyToken() {
        return spotifyToken;
    }

    public void setSpotifyToken(String spotifyToken) {
        this.spotifyToken = spotifyToken;
    }
}
