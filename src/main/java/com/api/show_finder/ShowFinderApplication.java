package com.api.show_finder;

import com.api.show_finder.client.EventimScraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.io.IOException;

@EnableFeignClients
@SpringBootApplication
public class ShowFinderApplication {

	public static void main(String[] args) {
		EventimScraper scraper = new EventimScraper();
		try {
			scraper.scrapeEventimShows();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SpringApplication.run(ShowFinderApplication.class, args);
	}

}
