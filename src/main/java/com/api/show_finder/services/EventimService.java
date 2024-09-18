package com.api.show_finder.services;

import com.api.show_finder.domain.model.ConcertDetails;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventimService {

    private static final String EVENTIM_API_URL = "https://public-api.eventim.com/websearch/search/api/exploration/v2/productGroups";

    public List<ConcertDetails> fetchInternationalShows() {
        List<ConcertDetails> concertDetailsList = new ArrayList<>();

        try {
            String webId = URLEncoder.encode("web__eventim-com-br", StandardCharsets.UTF_8);
            String language = URLEncoder.encode("pt", StandardCharsets.UTF_8);
            String retailPartner = URLEncoder.encode("BR1", StandardCharsets.UTF_8);
            String categories = URLEncoder.encode("Shows Internacionais", StandardCharsets.UTF_8);
            String sort = URLEncoder.encode("Recommendation", StandardCharsets.UTF_8);
            String inStock = URLEncoder.encode("true", StandardCharsets.UTF_8);

            String url = EVENTIM_API_URL + "?webId=" + webId + "&language=" + language + "&retail_partner=" + retailPartner + "&categories=" + categories + "&sort=" + sort + "&in_stock=" + inStock;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(new URI(url), String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            for (JsonNode productGroup : rootNode.get("productGroups")) {
                String artistAndCity = productGroup.get("name").asText();
                String eventDate = productGroup.get("startDate").asText();

                concertDetailsList.add(new ConcertDetails(artistAndCity, eventDate));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return concertDetailsList;
    }
}
