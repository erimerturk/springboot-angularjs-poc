package com.nar.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.nar.model.User;
import com.nar.security.AuthenticationService;
import com.nar.util.AjaxResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/hello")
    public Map<String, String> hello(){

        Map<String, String> result = new HashMap<String, String>();

        result.put("id", "1");
        result.put("content", "hello");

        return result;

    }

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        User currentUser = authenticationService.getCurrentUser();
        return user;
    }


}
