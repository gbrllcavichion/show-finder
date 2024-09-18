package com.api.show_finder.domain.model;

public class ConcertDetails {
    private String event;
    private String eventDate;

    public ConcertDetails(String event, String eventDate) {
        this.event = event;
        this.eventDate = eventDate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}
