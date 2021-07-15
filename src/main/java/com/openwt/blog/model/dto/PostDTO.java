package com.openwt.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
public class PostDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private long categoryId;
    @NotNull
    private Set<String> tags;
}

