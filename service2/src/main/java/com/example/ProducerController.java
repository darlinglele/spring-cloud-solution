package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @GetMapping("/")
    public String home() {
        return "hello world";
    }


    @GetMapping("/message")
    public String getMessage() {
        return "xxx";

    }
}