package com.api.show_finder.api.controller;

import com.api.show_finder.api.dto.ConcertDetails;
import com.api.show_finder.api.service.EventimScrapingService;
import com.api.show_finder.api.service.TicketScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowsController {
    private final TicketScrapingService ticketScrapingService;
    private final EventimScrapingService eventimScrapingService;

    public ShowsController(TicketScrapingService ticketScrapingService, EventimScrapingService eventimScrapingService) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimScrapingService = eventimScrapingService;
    }

    @GetMapping("/fetch-ticketmaster-concerts")
    public List<ConcertDetails> fetchConcerts() {
        return ticketScrapingService.fetchConcertDetails();
    }

    @GetMapping("/fetch-eventim-concerts")
    public List<ConcertDetails> fetchEventimConcerts() {
        return eventimScrapingService.fetchInternationalConcertDetails();
    }
}
