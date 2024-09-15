package com.api.show_finder.api.service;

import com.api.show_finder.api.dto.Artist;
import com.api.show_finder.api.dto.ConcertDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketScrapingService {

    private static final String URL = "https://www.ticketmaster.com.br";

    public List<ConcertDetails> fetchConcertDetails() {
        List<ConcertDetails> concertDetailsList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();

            Elements eventDetails = document.select(".block_content .details");

            for (Element event : eventDetails) {
                String artistAndCity = event.select("h3").text();
                String eventDate = event.select("strong").text();
                concertDetailsList.add(new ConcertDetails(artistAndCity, eventDate));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return concertDetailsList;
    }
}