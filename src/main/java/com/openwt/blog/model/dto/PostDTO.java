package com.openwt.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class PostDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private long categoryId;
    private String url;
    @NotNull
    private Set<String> tags;
}

