package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private Service2FeignClient service2FeignClient;

    @GetMapping("/")
    public String home() {
        return "hello world";
    }


    @GetMapping("/message")
    public String getMessage() {
        return service2FeignClient.getMessage();
    }
}