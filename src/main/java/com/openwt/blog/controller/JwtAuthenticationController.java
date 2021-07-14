package com.openwt.blog.controller;

import com.openwt.blog.model.JwtRequest;
import com.openwt.blog.model.JwtResponse;
import com.openwt.blog.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest login) {
        return jwtAuthenticationService.login(login);
    }
}