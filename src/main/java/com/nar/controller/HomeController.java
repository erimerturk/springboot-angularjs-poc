package com.nar.controller;


import com.nar.model.User;
import com.nar.model.dto.SignUpRequest;
import com.nar.security.AuthenticationService;
import com.nar.service.UserService;
import com.nar.util.AjaxResponse;
import com.nar.util.AjaxResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private UserService userService;

    private AuthenticationService authenticationService;

    private AjaxResponseUtils ajaxResponseUtils;

    @Autowired
    public HomeController(UserService userService, AuthenticationService authenticationService, AjaxResponseUtils ajaxResponseUtils) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.ajaxResponseUtils = ajaxResponseUtils;
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public ResponseEntity<AjaxResponse> register(@RequestBody SignUpRequest req) {
        userService.newUser(req);
        authenticationService.authenticate(req.getUsername(), req.getPassword());
        return ajaxResponseUtils.success()
                .build();
    }

}
