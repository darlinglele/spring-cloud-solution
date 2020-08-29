package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service2")
public interface Service2FeignClient {
    @GetMapping("/message")
    String getMessage();
}
