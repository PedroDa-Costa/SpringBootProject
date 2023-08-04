package com.capgemini.springbootproject.controller;

import com.capgemini.springbootproject.entity.Member;
import com.capgemini.springbootproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private MemberService memberService;

    @Autowired
    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "home";
    }


    @GetMapping("/")
    public String base() {
        return "redirect:/home";
    }

}
