package com.openwt.blog.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Data
public class PostDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Column(nullable = false)
    private long categoryId;
    @Column(nullable = false)
    private Collection<String> tags;
}
