package com.api.show_finder.domain.model;

public class ConcertDetails {
    private String event;
    private String eventDate;
    private String location;
    private String cityAbbreviation;

    public ConcertDetails(String event, String eventDate, String location, String city) {
        this.event = event;
        this.eventDate = eventDate;
        this.location = location;
        this.cityAbbreviation = city;
    }

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCityAbbreviation() { return cityAbbreviation; }
    public void setCityAbbreviation(String city) { this.cityAbbreviation = city; }
}
