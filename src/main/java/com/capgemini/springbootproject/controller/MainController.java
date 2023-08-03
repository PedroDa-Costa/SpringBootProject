package com.capgemini.springbootproject.controller;

import com.capgemini.springbootproject.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MainController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Member> members = getAllMembers();
        model.addAttribute("members", members);
        return "home";
    }

    private List<Member> getAllMembers() {
        return jdbcTemplate.query("SELECT username, email FROM members",
                (rs, rowNum) -> new Member(rs.getString("username"), rs.getString("email")));
    }

    @GetMapping("/")
    public String base() {
        return "redirect:/home";
    }

}
