package com.example.hello.controller;

import com.example.hello.HelloApplication;
import com.example.hello.model.HelloMessage;
import com.example.hello.model.Info;
import com.example.hello.repository.InfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RefreshScope
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private InfoRepository infoRepository;

    @GetMapping("/")
    public String home(){
        return "hello";
    }

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/message")
    public HelloMessage getMessage() {
        log.info("getMessage start.......");
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setName(getLocalInstanceInfo());
        helloMessage.setMessage(getInfoFromDatabase());
        log.info("getMessage end .......");
        return helloMessage;
    }


    private String getLocalInstanceInfo() {
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        return serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }


    private String getInfoFromDatabase() {
        List<Info> infoList = infoRepository.findByName(HelloApplication.KEY);
        for (Info info : infoList) {
            return info.toString();
        }
        return "(no database info)";
    }
}
