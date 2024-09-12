package com.api.show_finder.client;

import com.api.show_finder.api.dto.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventimScraper {

    public List<Event> scrapeEventimShows() throws IOException {
        String url = "https://www.eventim.com.br/events/shows-internacionais-156/";

        Document doc = Jsoup.connect(url)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
                .get();

        Elements shows = doc.select("article.listing-item");

        List<Event> events = new ArrayList<>();

        for (Element show : shows) {
            String artistName = show.select(".listing-item h2 span").text();
            String eventDate = show.select(".listing-description").text();

            System.out.println("Artista: " + artistName);
            System.out.println("Data: " + eventDate);
            System.out.println("------------");
            events.add(new Event(artistName, eventDate));
        }

        return events;
    }
}
