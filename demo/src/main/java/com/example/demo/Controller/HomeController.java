package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

// this is controller that belongs to Rasulg
// test test test

// i want add this controller
// test
// test

//test

public class HomeController {
    @GetMapping("/home")
    public String PageHome(){
        return "Hello world";
    }
}
