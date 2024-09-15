package com.api.show_finder.api.controller;

import com.api.show_finder.api.dto.ConcertDetails;
import com.api.show_finder.api.service.TicketScrapingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    private final TicketScrapingService scrapingService;

    public TicketController(TicketScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/fetch-concerts")
    public List<ConcertDetails> fetchConcerts() {
        return scrapingService.fetchConcertDetails();
    }
}
