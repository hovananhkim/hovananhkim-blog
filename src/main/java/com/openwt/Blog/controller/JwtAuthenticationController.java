package com.openwt.Blog.controller;

import com.openwt.Blog.config.JwtTokenProvider;
import com.openwt.Blog.model.JwtResponse;
import com.openwt.Blog.model.JwtRequest;
import com.openwt.Blog.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public String ok(){
        return "ok";
    }
    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @PostMapping("/test")
    public ResponseEntity<?> login(@RequestBody JwtRequest login) {
        System.out.println("ok");
        System.out.println(login.getEmail());
        System.out.println(login.getPassword());
        System.out.println(jwtUserDetailService.loadUserByUsername(login.getEmail()).getUsername());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                )
        );
        System.out.println(authentication);
        System.out.println("2");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
