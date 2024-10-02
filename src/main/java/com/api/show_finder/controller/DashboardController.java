package com.api.show_finder.controller;

import com.api.show_finder.domain.model.ConcertDetails;
import com.api.show_finder.services.ShowsService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    private final ShowsService showsService;

    public DashboardController(ShowsService showsService) {
        this.showsService = showsService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(@RequestParam("userId") String userId, Model model) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(userId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Parâmetro userId inválido.");
        }

        List<ConcertDetails> favoriteArtistShows = showsService.getUserFavoriteConcerts(objectId);

        model.addAttribute("favoriteArtistShows", favoriteArtistShows);
        return "dashboard";
    }

}

