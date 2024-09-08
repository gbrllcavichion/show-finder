package com.api.show_finder.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist {
    private String id;
    private String name;
    private int popularity;
    private List<String> genres;
    private String href;
    private Map<String, String> external_urls;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getHref() {
        return href;
    }

    public Map<String, String> getExternalUrls() {
        return external_urls;
    }
}
