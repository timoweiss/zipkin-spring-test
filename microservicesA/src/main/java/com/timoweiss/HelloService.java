package com.timoweiss;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloService {

    @RequestMapping("/hello")
    public String hello() {
        return "{\"timestamp\":\"" + new Date()
                + "\",\"content\":\"I'm okay ;-)\"}";
    }
}