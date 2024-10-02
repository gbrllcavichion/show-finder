package com.api.show_finder.domain.model;

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