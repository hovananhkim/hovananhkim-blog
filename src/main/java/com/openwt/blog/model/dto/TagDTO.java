package com.openwt.blog.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class TagDTO {
    private long id;
    @NotBlank
    private String name;
    private Set<String> posts;
}
