package com.api.show_finder.api.controller;

import com.api.show_finder.api.dto.ConcertDetails;
import com.api.show_finder.api.service.EventimService;
import com.api.show_finder.api.service.TicketScrapingService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowsController {
    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;

    public ShowsController(TicketScrapingService ticketScrapingService, EventimService eventimService) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
    }

    @GetMapping("/fetch-ticketmaster-concerts")
    public List<ConcertDetails> fetchConcerts() {
        return ticketScrapingService.fetchConcertDetails();
    }

    @GetMapping("/fetch-international-shows")
    public String fetchInternationalShows() {
        return eventimService.fetchInternationalShows();
    }
}
