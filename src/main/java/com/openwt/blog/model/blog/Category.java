package com.openwt.blog.model.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private Date createDate = new Date();

    @Column(nullable = false)
    private Date updateDate = new Date();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Post> posts;
}
