package com.timoweiss;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class HelloService {

    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @RequestMapping(value = "/dostuff", method = RequestMethod.GET)
    public ResponseEntity<String> doStuff() {

        ServiceInstance instance = loadBalancerClient.choose("hello-service");

        if(instance != null) {
            ResponseEntity<String> entity = restTemplate.getForEntity(instance.getUri() + "/hello", String.class);
            System.out.println(entity.getBody() + " " + entity.getStatusCode());
            return entity;
        }

        return null;
    }

}
