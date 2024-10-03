package com.api.show_finder.controller;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.services.EventimService;
import com.api.show_finder.services.TicketScrapingService;
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
    public List<ConcertDetails> fetchTicketMasterConcerts() {
        return ticketScrapingService.fetchConcertDetails();
    }

    @GetMapping("/fetch-eventim-shows")
    public List<ConcertDetails> fetchEventimShows() {
        return eventimService.fetchInternationalShows();
    }
}
