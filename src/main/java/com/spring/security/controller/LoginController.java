package com.spring.security.controller;

import com.spring.security.dto.Signup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
class LoginController {
    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("login",new Signup());
        return "login.html";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("login") Signup signup, Model model){
        return "welcome";
    }

    @GetMapping("/")
    public String home(){
        return "welcome";
    }

}