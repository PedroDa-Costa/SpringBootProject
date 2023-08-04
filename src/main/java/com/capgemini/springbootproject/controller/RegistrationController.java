package com.capgemini.springbootproject.controller;

import com.capgemini.springbootproject.entity.Authority;
import com.capgemini.springbootproject.entity.Member;
import com.capgemini.springbootproject.service.AuthorityService;
import com.capgemini.springbootproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private MemberService memberService;
    private AuthorityService authorityService;


    @Autowired
    public RegistrationController(MemberService memberService, AuthorityService authorityService) {
        this.memberService = memberService;
        this.authorityService = authorityService;
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("email") String email, Model model) {

        String encryptedPassword = "{bcrypt}" + new BCryptPasswordEncoder().encode(password);

        Member member = new Member(username, encryptedPassword, email, 1);

        //check if member already exists
        int id = memberService.findIdByMember(member);

        //if -1 no member with that username
        if(id == -1) {
            memberService.save(member);
            //check new member id to use in authority
            id = memberService.findIdByMember(member);
            Authority authority = new Authority(id, "ROLE_ADMIN");
            authorityService.save(authority);
            return "redirect:/loginForm";
        }
        model.addAttribute("text", "Member already exists with that username");
        return "registration";

    }
}
