package com.openwt.blog.model;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}

