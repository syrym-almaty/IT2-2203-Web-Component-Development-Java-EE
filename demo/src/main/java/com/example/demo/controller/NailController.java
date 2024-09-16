package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NailController {

//    Rasul was here


//    Rasul

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Rasul!";
    }
}
