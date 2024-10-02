package com.api.show_finder.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class AuthController {

    @GetMapping("/login-success")
    public void loginSuccess(HttpServletResponse response) throws IOException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        response.sendRedirect("http://localhost:3000/dashboard?userId=" + userId);
    }
}
