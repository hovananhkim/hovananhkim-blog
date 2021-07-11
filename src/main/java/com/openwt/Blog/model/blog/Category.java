package com.openwt.Blog.model.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private Date createDate = new Date();

    @Column(nullable = false)
    private Date updateDate = new Date();

    @OneToMany(mappedBy = "category")
    private Set<Post> posts;
}
