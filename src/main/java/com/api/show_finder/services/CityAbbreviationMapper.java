package com.api.show_finder.services;

import java.util.HashMap;
import java.util.Map;

public class CityAbbreviationMapper {

    private static final Map<String, String> cityAbbreviationMap = new HashMap<>();

    static {
        cityAbbreviationMap.put("São Paulo", "SP");
        cityAbbreviationMap.put("Curitiba", "CWB");
        cityAbbreviationMap.put("Rio de Janeiro", "RJ");
        cityAbbreviationMap.put("Belo Horizonte", "BH");
        cityAbbreviationMap.put("Porto Alegre", "POA");
    }

    public static String getCityAbbreviation(String cityName) {
        return cityAbbreviationMap.getOrDefault(cityName, cityName);
    }
}