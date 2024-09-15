package com.api.show_finder.api.service;

import com.api.show_finder.api.dto.ConcertDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventimScrapingService {

    private static final String URL_INTERNACIONAL = "https://www.eventim.com.br/events/shows-internacionais-156/";

    public List<ConcertDetails> fetchInternationalConcertDetails() {
        List<ConcertDetails> concertDetailsList = new ArrayList<>();

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        try {
            driver.get(URL_INTERNACIONAL);

            Thread.sleep(5000);

            List<WebElement> eventItems = driver.findElements(By.cssSelector("article.listing-item"));

            for (WebElement eventItem : eventItems) {
                String artistName = eventItem.findElement(By.cssSelector("span.theme-text-color")).getText();
                String cityAndDate = eventItem.findElement(By.cssSelector("div.event-listing-city")).getText();

                concertDetailsList.add(new ConcertDetails(artistName, cityAndDate));
            }

        } catch (InterruptedException e) {
            System.out.println("Erro durante a espera pela página carregar.");
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return concertDetailsList;
    }
}