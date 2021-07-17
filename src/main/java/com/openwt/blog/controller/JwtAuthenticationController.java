package com.openwt.blog.controller;

import com.openwt.blog.model.JwtRequest;
import com.openwt.blog.model.JwtResponse;
import com.openwt.blog.model.user.User;
import com.openwt.blog.service.JwtAuthenticationService;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class JwtAuthenticationController {
    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest login) {
        return jwtAuthenticationService.login(login);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return userService.save(user);
    }

}
