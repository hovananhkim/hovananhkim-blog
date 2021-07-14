package com.openwt.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class PostDTO {
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private String category;
    @NotNull
    private String email;
    @NotNull
    private Set<String> tags;
}

