package com.api.show_finder.domain.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

public class User {

    @Id
    private ObjectId id;
    private String email;
    private String username;
    private String password;
    private String displayName;
    private List<String> favoriteArtists;
    private String spotifyToken;
    public User() {

    }
    public User(ObjectId id, String email, String username, String password, String displayName, List<String> favoriteArtists, String spotifyToken) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.favoriteArtists = favoriteArtists;
        this.spotifyToken = spotifyToken;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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
