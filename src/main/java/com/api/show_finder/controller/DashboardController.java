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
    public String getDashboard(@RequestParam("userId") ObjectId userId, Model model) {
        List<ConcertDetails> favoriteArtistShows = showsService.getUserFavoriteConcerts(userId);

        model.addAttribute("favoriteArtistShows", favoriteArtistShows);

        return "dashboard";
    }
}
