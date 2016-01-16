package com.nar.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public Map<String, String> hello(){

        Map<String, String> result = new HashMap<String, String>();

        result.put("id", "1");
        result.put("content", "hello");

        return result;

    }

}
