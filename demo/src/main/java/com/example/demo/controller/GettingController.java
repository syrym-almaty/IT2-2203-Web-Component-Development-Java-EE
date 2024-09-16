package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GettingController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

}