package com.capgemini.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public RegistrationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        jdbcTemplate.update(
                "INSERT INTO members (username, password, email, enabled) VALUES (?, ?, ?, ?)",
                username, "{bcrypt}" + encryptedPassword, email, 1
        );

        jdbcTemplate.update(
                "INSERT INTO authorities (id, authority) VALUES ((SELECT id FROM members WHERE username = ?), 'ROLE_ADMIN')",
                username
        );

        return "redirect:/loginForm";
    }
}
