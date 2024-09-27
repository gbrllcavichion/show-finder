package com.api.show_finder.services;

import com.api.show_finder.domain.model.ConcertDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowsService {

    private final TicketScrapingService ticketScrapingService;
    private final EventimService eventimService;

    public ShowsService(TicketScrapingService ticketScrapingService, EventimService eventimService) {
        this.ticketScrapingService = ticketScrapingService;
        this.eventimService = eventimService;
    }

    public List<ConcertDetails> getAllAvailableConcerts() {
        List<ConcertDetails> ticketMasterConcerts = ticketScrapingService.fetchConcertDetails();
        List<ConcertDetails> eventimConcerts = eventimService.fetchInternationalShows();

        List<ConcertDetails> allConcerts = new ArrayList<>();
        allConcerts.addAll(ticketMasterConcerts);
        allConcerts.addAll(eventimConcerts);

        return allConcerts;
    }
}
