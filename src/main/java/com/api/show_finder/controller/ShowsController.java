package com.api.show_finder.controller;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.services.EventimService;
import com.api.show_finder.services.TicketScrapingService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
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
    public List<ConcertDetails> fetchInternationalShows() {
        return eventimService.fetchInternationalShows();
    }
}
