package com.api.show_finder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String redirectToDashboard() {
        return "redirect:http://localhost:3000/dashboard";
    }
}
