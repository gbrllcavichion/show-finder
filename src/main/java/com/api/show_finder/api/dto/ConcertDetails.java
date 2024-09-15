package com.api.show_finder.api.dto;

public class ConcertDetails {
    private String artistAndCity;
    private String eventDate;

    public ConcertDetails(String artistAndCity, String eventDate) {
        this.artistAndCity = artistAndCity;
        this.eventDate = eventDate;
    }

    public String getArtistAndCity() {
        return artistAndCity;
    }

    public void setArtistAndCity(String artistAndCity) {
        this.artistAndCity = artistAndCity;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
