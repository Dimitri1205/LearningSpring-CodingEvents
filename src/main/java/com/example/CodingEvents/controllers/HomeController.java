package com.example.CodingEvents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("title", "Coding Events");
        return "index";
    }

}