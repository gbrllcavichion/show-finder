package com.api.show_finder.api.response;

import com.api.show_finder.api.dto.Artist;

import java.util.List;

public class SpotifyTopArtistsResponse {
    private List<Artist> items;

    public List<Artist> getItems() {
        return items;
    }

    public void setItems(List<Artist> items) {
        this.items = items;
    }
}