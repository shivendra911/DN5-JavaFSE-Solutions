package com.cognizant.greetservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    // GET http://localhost:9091/greet
    // also reachable through the gateway at http://localhost:9090/greet-service/greet
    @GetMapping("/greet")
    public String greet() {
        return "Hello World";
    }
}
