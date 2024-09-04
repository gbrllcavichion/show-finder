package com.api.show_finder.api.dto;

import java.util.List;
import java.util.Map;

public class Artist {
    private String id;
    private String name;
    private int popularity;
    private List<String> genres;
    private String href;
    private Map<String, String> external_urls;
}
