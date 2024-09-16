package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Rasul was here

@RestController
public class NailController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
